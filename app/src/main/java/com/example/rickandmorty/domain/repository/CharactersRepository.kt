package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.characters.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun fetchAllCharacters(): Flow<PagingData<Character>>
}