package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.remote.dto.CharacterDTO
import com.example.rickandmorty.data.remote.dto.CharactersDTO
import com.example.rickandmorty.data.remote.dto.EpisodeDTO
import com.example.rickandmorty.data.remote.dto.EpisodesDTO
import com.example.rickandmorty.data.remote.dto.LocationDTO
import com.example.rickandmorty.data.remote.dto.LocationsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    suspend fun fetchCharactersByPage(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): Response<CharactersDTO>

    @GET("character/{id}")
    suspend fun fetchCharacterByID(@Path("id") id: Int): Response<CharacterDTO>

    @GET("location")
    suspend fun fetchLocationsByPage(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("type") type: String? = null,
        @Query("dimension") dimension: String? = null
    ): Response<LocationsDTO>

    @GET("location/{id}")
    suspend fun fetchLocationByID(@Path("id") id: Int): Response<LocationDTO>

    @GET("episode")
    suspend fun fetchEpisodesByPage(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("episode") episode: String? = null
    ): Response<EpisodesDTO>

    @GET("episode/{id}")
    suspend fun fetchEpisodeByID(@Path("id") id: Int): Response<EpisodeDTO>
}