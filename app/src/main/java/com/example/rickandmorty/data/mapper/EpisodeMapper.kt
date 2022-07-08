package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.remote.dto.EpisodeDTO
import com.example.rickandmorty.domain.episodes.model.Episode

fun EpisodeDTO.toEpisode(): Episode {
    return Episode(
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters
    )
}