package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.local.RickAndMortyDatabase

class RickAndMortyApplication: Application() {
    val database: RickAndMortyDatabase by lazy {RickAndMortyDatabase.getInstance(this) }
}