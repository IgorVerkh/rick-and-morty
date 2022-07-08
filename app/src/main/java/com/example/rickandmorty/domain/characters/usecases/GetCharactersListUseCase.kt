package com.example.rickandmorty.domain.characters.usecases

import com.example.rickandmorty.domain.characters.model.Character

interface GetCharactersListUseCase {
    suspend operator fun invoke(startId: Int, lastId: Int): List<Character>
}