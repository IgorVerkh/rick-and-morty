package com.example.rickandmorty.presentation.mapper

import android.util.Log
import com.example.rickandmorty.domain.characters.model.Character
import com.example.rickandmorty.presentation.characters.model.CharacterListItem

fun Character.toCharacterListItem(): CharacterListItem {
    return CharacterListItem(
        id = id,
        name = name,
        species = species,
        status = status,
        gender = gender,
        image = image
    )
}