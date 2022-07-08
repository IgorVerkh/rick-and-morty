package com.example.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.util.Constants.LOCATIONS_REMOTE_KEYS

@Entity(tableName = LOCATIONS_REMOTE_KEYS)
data class LocationsRemoteKeys (
    @PrimaryKey
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)