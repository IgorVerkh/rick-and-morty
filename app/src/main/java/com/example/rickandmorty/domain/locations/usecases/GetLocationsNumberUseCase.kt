package com.example.rickandmorty.domain.locations.usecases

interface GetLocationsNumberUseCase {
    suspend operator fun invoke(): Int
}