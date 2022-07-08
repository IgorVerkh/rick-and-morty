package com.example.rickandmorty.domain.locations.model

data class Location(
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)
