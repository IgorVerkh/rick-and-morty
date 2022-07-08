package com.example.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.local.entities.EpisodesRemoteKeys

@Dao
interface EpisodesRemoteKeysDao {

    @Query("SELECT * FROM episodes_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): EpisodesRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<EpisodesRemoteKeys>)

    @Query("DELETE FROM episodes_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}