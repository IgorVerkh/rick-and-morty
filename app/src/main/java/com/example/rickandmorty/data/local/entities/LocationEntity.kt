package com.example.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.util.Constants.LOCATIONS_TABLE

@Entity(tableName = LOCATIONS_TABLE)
data class LocationEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)
