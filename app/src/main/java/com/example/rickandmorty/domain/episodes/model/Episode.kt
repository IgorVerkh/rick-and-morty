package com.example.rickandmorty.domain.episodes.model

data class Episode(
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>
)
