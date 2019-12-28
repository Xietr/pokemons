package com.example.data.caching.room.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.caching.room.PokemonCardConverter
import com.example.data.caching.room.daos.PokemonCardDao
import com.example.data.caching.room.entities.PokemonCardRoomEntity

@Database(entities = [PokemonCardRoomEntity::class], version = 1, exportSchema = false)
@TypeConverters(PokemonCardConverter::class)
abstract class PokemonCardDataBase : RoomDatabase() {

    abstract fun pokemonCardDao(): PokemonCardDao
}
