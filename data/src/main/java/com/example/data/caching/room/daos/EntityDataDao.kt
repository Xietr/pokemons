package com.example.data.caching.room.daos

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EntityDataDao {

    @Insert(onConflict = REPLACE)
    fun save(entity: Entity): Completable

    @Query("SELECT * from Entity")
    fun getAll(): Single<Entity>

    @Query("DELETE from Entity")
    fun deleteAll()
}