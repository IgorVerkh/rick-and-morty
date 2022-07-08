package com.example.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.data.local.entities.CharacterEntity

@Dao
interface CharacterDao {


    @Query("SELECT * FROM characters_table")
    fun getAllCharacters(): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Delete
    suspend fun delete(character: CharacterEntity)

    @Query("DELETE FROM characters_table")
    suspend fun deleteAllCharacters()
}