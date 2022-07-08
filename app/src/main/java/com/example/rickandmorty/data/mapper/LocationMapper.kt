package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.remote.dto.LocationDTO
import com.example.rickandmorty.domain.locations.model.Location

fun LocationDTO.toLocation(): Location {
    return Location(
        name = name,
        type = type,
        dimension = dimension,
        residents = residents
    )
}