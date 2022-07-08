package com.example.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.util.Constants.CHARACTERS_REMOTE_KEYS

@Entity(tableName = CHARACTERS_REMOTE_KEYS)
data class CharactersRemoteKeys (
    @PrimaryKey
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)