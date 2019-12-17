package com.example.data.caching.room.databases

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.example.data.caching.room.daos.EntityDataDao

@Database(entities = [Entity::class], version = 1)
abstract class EntityDataBase : RoomDatabase() {

    abstract fun weatherDataDao(): EntityDataDao
}