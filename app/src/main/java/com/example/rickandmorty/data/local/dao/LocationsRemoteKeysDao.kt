package com.example.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.local.entities.LocationsRemoteKeys

@Dao
interface LocationsRemoteKeysDao {

    @Query("SELECT * FROM locations_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): LocationsRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<LocationsRemoteKeys>)

    @Query("DELETE FROM locations_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}