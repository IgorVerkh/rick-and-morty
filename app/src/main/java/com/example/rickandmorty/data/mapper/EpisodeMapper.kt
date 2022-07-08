package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.local.entities.EpisodeEntity
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

fun EpisodeDTO.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters
    )
}