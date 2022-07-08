package com.example.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.data.local.entities.LocationEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations_table")
    fun getAllLocations(): PagingSource<Int, LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<LocationEntity>)

    @Delete
    suspend fun delete(location: LocationEntity)
}