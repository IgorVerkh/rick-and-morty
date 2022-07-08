package com.example.rickandmorty.domain.locations.usecases

import com.example.rickandmorty.domain.locations.model.Location

interface GetSingleLocationUseCase {
    suspend operator fun invoke(id: Int): Location
}