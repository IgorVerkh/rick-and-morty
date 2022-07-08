package com.example.rickandmorty.data.local.dao

import androidx.room.TypeConverter

class TypeConverters {

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(";")
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return string.split(";")
    }
}