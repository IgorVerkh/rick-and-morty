package com.example.rickandmorty.domain.episodes.usecases

import com.example.rickandmorty.domain.episodes.model.Episode

interface GetSingleEpisodeUseCase {
    suspend operator fun invoke(id:Int): Episode
}