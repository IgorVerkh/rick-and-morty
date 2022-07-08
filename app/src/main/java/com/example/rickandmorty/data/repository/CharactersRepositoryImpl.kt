package com.example.rickandmorty.data.repository

import androidx.paging.*
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.data.local.RickAndMortyDatabase
import com.example.rickandmorty.data.mapper.toCharacter
import com.example.rickandmorty.data.paging.CharactersRemoteMediator
import com.example.rickandmorty.data.remote.RickAndMortyApi
import com.example.rickandmorty.data.remote.RickAndMortyApiService
import com.example.rickandmorty.data.util.Constants.PAGE_SIZE
import com.example.rickandmorty.domain.characters.model.Character
import com.example.rickandmorty.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class CharactersRepositoryImpl(
    private val application: RickAndMortyApplication,
    private val rickAndMortyApiService: RickAndMortyApiService = RickAndMortyApi.remoteService,
    private val rickAndMortyDatabase: RickAndMortyDatabase = application.database
): CharactersRepository {

        override fun fetchAllCharacters(): Flow<PagingData<Character>> {

            val pagingSourceFactory = { rickAndMortyDatabase.characterDao().getAllCharacters() }

            return Pager(
                config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
                remoteMediator = CharactersRemoteMediator(
                    rickAndMortyApiService,
                    rickAndMortyDatabase
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map { characterEntity ->
                    characterEntity.toCharacter() } }
    }

}