package com.example.rickandmorty.data.remote.page_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.remote.RickAndMortyApiService
import com.example.rickandmorty.domain.episodes.model.Episode

class EpisodesPageSource(
    private val rickAndMortyApiService: RickAndMortyApiService,
    private val query: String
): PagingSource<Int, List<Episode>>() {
    override fun getRefreshKey(state: PagingState<Int, List<Episode>>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<Episode>> {
        TODO("Not yet implemented")
    }
}