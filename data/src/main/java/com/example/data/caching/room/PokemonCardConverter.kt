package com.example.data.caching.room

import androidx.room.TypeConverter
import com.example.domain.entities.Attack
import com.example.domain.entities.Weakness
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object PokemonCardConverter {

    private val gson = Gson()


    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: List<String?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return if (value.isNullOrEmpty()) {
            emptyList()
        } else {
            gson.fromJson(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromAttackList(list: List<Attack?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToAttack(value: String): List<Attack> {
        val listType = object : TypeToken<List<Attack>>() {}.type
        return if (value.isNullOrEmpty()) {
            emptyList()
        } else {
            gson.fromJson(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromWeaknessList(list: List<Weakness?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToWeakness(value: String): List<Weakness> {
        val listType = object : TypeToken<List<Weakness>>() {}.type
        return if (value.isNullOrEmpty()) {
            emptyList()
        } else {
            gson.fromJson(value, listType)
        }
    }
}