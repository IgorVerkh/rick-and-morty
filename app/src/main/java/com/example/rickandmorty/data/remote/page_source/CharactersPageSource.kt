package com.example.rickandmorty.data.remote.page_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.mapper.toCharacter
import com.example.rickandmorty.data.remote.RickAndMortyApiService
import com.example.rickandmorty.domain.characters.model.Character
import retrofit2.HttpException

class CharactersPageSource(
    private val rickAndMortyApiService: RickAndMortyApiService,
    private val query: String
): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page: Int = params.key ?: 1

        val response = rickAndMortyApiService.fetchCharactersByPage(page)
        return if (response.isSuccessful) {
            val characters: List<Character> = checkNotNull(response.body()).results.map { it.toCharacter() }
            val info = checkNotNull(response.body()).info
            val nextKey = if (info.next != null) page + 1 else null
            val prevKey = if (info.prev != null) page - 1 else null

            LoadResult.Page(characters, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }
}