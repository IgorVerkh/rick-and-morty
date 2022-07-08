package com.example.rickandmorty.data.remote.dto

data class InfoDTO(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
