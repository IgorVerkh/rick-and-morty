package com.example.rickandmorty.presentation.characters.model

import android.graphics.Bitmap

data class CharacterListItem(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String
)