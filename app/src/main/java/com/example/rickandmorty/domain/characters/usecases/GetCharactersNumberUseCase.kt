package com.example.rickandmorty.domain.characters.usecases

interface GetCharactersNumberUseCase {
    suspend operator fun invoke(): Int
}