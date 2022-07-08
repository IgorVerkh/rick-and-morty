package com.example.rickandmorty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.RickAndMortyDatabase
import com.example.rickandmorty.data.local.entities.EpisodeEntity
import com.example.rickandmorty.data.local.entities.EpisodesRemoteKeys
import com.example.rickandmorty.data.mapper.toEpisodeEntity
import com.example.rickandmorty.data.remote.RickAndMortyApiService
import retrofit2.HttpException

@ExperimentalPagingApi
class EpisodesRemoteMediator(
    private val rickAndMortyApi: RickAndMortyApiService,
    private val rickAndMortyDatabase: RickAndMortyDatabase
): RemoteMediator<Int, EpisodeEntity>() {

    private val episodeDao = rickAndMortyDatabase.episodeDao()
    private val episodesRemoteKeysDao = rickAndMortyDatabase.episodesRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = rickAndMortyApi.fetchEpisodesByPage(page = page)
            if (response.isSuccessful) {
                val episodes = checkNotNull(response.body()).results
                val info = checkNotNull(response.body()).info

                val prevPage = if (info.prev != null) page - 1 else null
                val nextPage = if (info.next != null) page + 1 else null

                val endOfPaginationReached = nextPage == null

                rickAndMortyDatabase.withTransaction {
                    // clear all tables in the database
                    if (loadType == LoadType.REFRESH) {
                        episodeDao.getAllEpisodes()
                        episodesRemoteKeysDao.deleteAllRemoteKeys()
                    }
                }
                val keys = episodes.map { episodeDto ->
                    EpisodesRemoteKeys(
                        id = episodeDto.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                episodesRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                episodeDao.insertEpisodes(episodes = episodes.map { it.toEpisodeEntity() })

                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }

            MediatorResult.Error(HttpException(response))
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EpisodeEntity>
    ): EpisodesRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                episodesRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, EpisodeEntity>
    ): EpisodesRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { episode ->
                episodesRemoteKeysDao.getRemoteKeys(id = episode.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, EpisodeEntity>
        ): EpisodesRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { episode ->
                episodesRemoteKeysDao.getRemoteKeys(id = episode.id)
            }
    }
}