package com.example.rickandmorty.domain.episodes.usecases

import com.example.rickandmorty.domain.episodes.model.Episode

interface GetEpisodesListUseCase {
    suspend operator fun invoke(startId: Int, lastId: Int): List<Episode>
}