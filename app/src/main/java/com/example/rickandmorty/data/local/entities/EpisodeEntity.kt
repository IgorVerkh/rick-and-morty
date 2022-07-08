package com.example.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.util.Constants.EPISODES_TABLE

@Entity(tableName = EPISODES_TABLE)
data class EpisodeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>
)
