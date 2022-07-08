package com.example.rickandmorty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.RickAndMortyDatabase
import com.example.rickandmorty.data.local.entities.CharacterEntity
import com.example.rickandmorty.data.local.entities.CharactersRemoteKeys
import com.example.rickandmorty.data.mapper.toCharacter
import com.example.rickandmorty.data.mapper.toCharacterEntity
import com.example.rickandmorty.data.remote.RickAndMortyApiService
import com.example.rickandmorty.domain.characters.model.Character
import retrofit2.HttpException

@ExperimentalPagingApi
class CharactersRemoteMediator(
    private val rickAndMortyApi: RickAndMortyApiService,
    private val rickAndMortyDatabase: RickAndMortyDatabase
): RemoteMediator<Int, CharacterEntity>() {

    private val characterDao = rickAndMortyDatabase.characterDao()
    private val charactersRemoteKeysDao = rickAndMortyDatabase.charactersRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
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

            val response = rickAndMortyApi.fetchCharactersByPage(page = page)
            if (response.isSuccessful) {
                val characters = checkNotNull(response.body()).results
                val info = checkNotNull(response.body()).info

                val prevPage = if (info.prev != null) page - 1 else null
                val nextPage = if (info.next != null) page + 1 else null

                val endOfPaginationReached = nextPage == null

                rickAndMortyDatabase.withTransaction {
                    // clear all tables in the database
                    if (loadType == LoadType.REFRESH) {
                        characterDao.deleteAllCharacters()
                        charactersRemoteKeysDao.deleteAllRemoteKeys()
                    }
            }
                val keys = characters.map { characterDto ->
                    CharactersRemoteKeys(
                        id = characterDto.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                charactersRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                characterDao.insertCharacters(characters = characters.map { it.toCharacterEntity() })

                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }

            MediatorResult.Error(HttpException(response))
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterEntity>
    ): CharactersRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                charactersRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterEntity>
    ): CharactersRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                charactersRemoteKeysDao.getRemoteKeys(id = character.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterEntity>
    ): CharactersRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                charactersRemoteKeysDao.getRemoteKeys(id = character.id)
            }
    }
}