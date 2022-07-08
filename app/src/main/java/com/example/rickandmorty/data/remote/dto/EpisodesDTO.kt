package com.example.rickandmorty.data.remote.dto

import com.squareup.moshi.Json

data class EpisodesDTO(
    val info: InfoDTO,
    val results: List<EpisodeDTO>
)

data class EpisodeDTO(
    val id: Int,
    val name: String,
    @Json(name = "air_date") val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)