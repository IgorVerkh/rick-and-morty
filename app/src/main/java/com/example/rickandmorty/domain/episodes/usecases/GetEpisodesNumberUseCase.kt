package com.example.rickandmorty.domain.episodes.usecases

interface GetEpisodesNumberUseCase {
    suspend operator fun invoke(): Int
}