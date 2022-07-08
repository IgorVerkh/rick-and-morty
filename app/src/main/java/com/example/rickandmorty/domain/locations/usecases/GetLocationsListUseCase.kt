package com.example.rickandmorty.domain.locations.usecases

import com.example.rickandmorty.domain.locations.model.Location

interface GetLocationsListUseCase {
    suspend operator fun invoke(startId: Int, lastId: Int): List<Location>
}