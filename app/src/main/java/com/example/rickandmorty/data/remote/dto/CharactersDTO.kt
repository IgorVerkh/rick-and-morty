package com.example.rickandmorty.data.remote.dto

data class CharactersDTO(
    val info: InfoDTO,
    val results: List<CharacterDTO>
)

data class CharacterDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) {
    data class Location(
        val name: String,
        val url: String
    )
}
