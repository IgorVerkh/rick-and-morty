package com.example.rickandmorty.data.remote.page_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.remote.RickAndMortyApiService
import com.example.rickandmorty.domain.locations.model.Location

class LocationsPageSource(
    private val rickAndMortyApiService: RickAndMortyApiService,
    private val query: String
): PagingSource<Int, List<Location>>() {
    override fun getRefreshKey(state: PagingState<Int, List<Location>>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<Location>> {
        TODO("Not yet implemented")
    }
}