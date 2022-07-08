package com.example.rickandmorty.presentation.locations.model

import android.graphics.Bitmap

data class LocationDetails(
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Character>
) {
    data class Character(
        val name: String,
        val image: Bitmap
    )
}
