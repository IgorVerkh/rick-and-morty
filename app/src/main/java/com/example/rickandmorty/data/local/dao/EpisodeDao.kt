package com.example.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.data.local.entities.EpisodeEntity

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episodes_table")
    fun getAllEpisodes(): PagingSource<Int, EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    @Delete
    suspend fun delete(episode: EpisodeEntity)
}