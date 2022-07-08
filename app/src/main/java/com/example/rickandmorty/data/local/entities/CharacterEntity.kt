package com.example.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.util.Constants.CHARACTERS_TABLE

@Entity(tableName = CHARACTERS_TABLE)
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episode: List<String>,
)
