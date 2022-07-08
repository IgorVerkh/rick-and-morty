package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.local.entities.CharacterEntity
import com.example.rickandmorty.data.remote.dto.CharacterDTO
import com.example.rickandmorty.domain.characters.model.Character

fun CharacterDTO.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.url,
        location = location.url,
        image = image,
        episode = episode
    )
}

fun CharacterDTO.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.url,
        location = location.url,
        image = image,
        episode = episode
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        image = image,
        episode = episode
    )
}