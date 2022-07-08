package com.example.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.util.Constants.EPISODES_REMOTE_KEYS

@Entity(tableName = EPISODES_REMOTE_KEYS)
data class EpisodesRemoteKeys (
    @PrimaryKey
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)