package com.example.data.caching.room

import androidx.room.TypeConverter
import com.example.domain.entities.Attack
import com.example.domain.entities.Weakness
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONObject


object PokemonCardConverter {

    //    private val gson = Gson()
    private val gson = GsonBuilder().serializeNulls().create()


    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: List<String?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): List<String> {
        return if (value.isNullOrEmpty() || value == "null") {
            emptyList()
        } else {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromAttackList(list: List<Attack?>?): String {
        list?.filter { it?.convertedEnergyCost != JSONObject.NULL }
//        list?.filter { it? }
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToAttack(value: String?): List<Attack> {
        return if (value.isNullOrEmpty() || value == "null") {
            emptyList()
        } else {
            val listType = object : TypeToken<List<Attack>>() {}.type
            gson.fromJson<List<Attack>>(value, listType)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromWeaknessList(list: List<Weakness?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToWeakness(value: String?): List<Weakness> {
        return if (value.isNullOrEmpty() || value == "null") {
            emptyList()
        } else {
            val listType = object : TypeToken<List<Weakness>>() {}.type
            gson.fromJson(value, listType)
        }
    }
}