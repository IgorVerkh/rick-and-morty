package com.example.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.local.dao.*
import com.example.rickandmorty.data.local.entities.*
import com.example.rickandmorty.data.util.Constants.RICK_AND_MORTY_DATABASE

@Database(entities = [
    CharacterEntity::class,
    CharactersRemoteKeys::class,
    EpisodeEntity::class,
    EpisodesRemoteKeys::class,
    LocationEntity::class,
    LocationsRemoteKeys::class], version = 1)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class RickAndMortyDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun charactersRemoteKeysDao(): CharactersRemoteKeysDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun episodesRemoteKeysDao(): EpisodesRemoteKeysDao
    abstract fun locationDao(): LocationDao
    abstract fun locationsRemoteKeysDao(): LocationsRemoteKeysDao

    companion object{

        @Volatile
        private var INSTANCE: RickAndMortyDatabase? = null

        fun getInstance(context: Context): RickAndMortyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    RickAndMortyDatabase::class.java,
                    RICK_AND_MORTY_DATABASE
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}