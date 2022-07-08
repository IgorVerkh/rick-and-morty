package com.example.rickandmorty.presentation.characters.model

import android.graphics.Bitmap

data class CharacterDetails(
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val type: String,
    val location: String,
    val origin: String,
    val episodes: List<String>,
    val image: Bitmap
)
