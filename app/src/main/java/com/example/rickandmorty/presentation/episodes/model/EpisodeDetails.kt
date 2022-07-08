package com.example.rickandmorty.presentation.episodes.model

import android.graphics.Bitmap

data class EpisodeDetails(
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<Character>
) {
    data class Character(
        val name: String,
        val image: Bitmap
    )
}
