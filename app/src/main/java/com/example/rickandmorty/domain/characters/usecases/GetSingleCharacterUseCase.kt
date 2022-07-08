package com.example.rickandmorty.domain.characters.usecases

import com.example.rickandmorty.domain.characters.model.Character

interface GetSingleCharacterUseCase {
    suspend operator fun invoke(id: Int): Character
}