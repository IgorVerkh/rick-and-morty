package com.example.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.local.entities.CharactersRemoteKeys

@Dao
interface CharactersRemoteKeysDao {

    @Query("SELECT * FROM characters_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): CharactersRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharactersRemoteKeys>)

    @Query("DELETE FROM characters_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}