package com.example.rickandmorty.data.remote.dto

data class LocationsDTO (
    val info: InfoDTO,
    val results: List<LocationDTO>
)

data class LocationDTO(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)
