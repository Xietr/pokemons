package com.example.data.caching.room.daos

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy.*
import androidx.room.Query
import com.example.data.caching.room.entities.PokemonCardRoomEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PokemonCardDao {

    @Insert(onConflict = IGNORE)
    fun saveCards(vararg cards: PokemonCardRoomEntity?): Completable

    @Insert//(onConflict = ABORT)
    fun saveCard(card: PokemonCardRoomEntity?): Completable

    @Query("UPDATE pokemon SET isFavorite = :isFavorite WHERE id = :id")
    fun updateCard(id: String, isFavorite: Int): Completable

    @Query("SELECT * from pokemon LIMIT :pageSize OFFSET :skipItems")
    fun getCards(pageSize: Int, skipItems: Int): Single<List<PokemonCardRoomEntity>>

    @Query("SELECT * from pokemon WHERE name = :name LIMIT :pageSize OFFSET :skipItems")
    fun getCardsByName(
        pageSize: Int,
        skipItems: Int,
        name: String
    ): Single<List<PokemonCardRoomEntity>>

    @Query("SELECT * from pokemon WHERE isFavorite = 1 LIMIT :pageSize OFFSET :skipItems")
    fun getFavoriteCards(
        pageSize: Int,
        skipItems: Int
    ): Single<List<PokemonCardRoomEntity>>

    @Query("SELECT * from pokemon WHERE id = :id")
    fun getCardsById(id: String): Single<PokemonCardRoomEntity>

    @Query("DELETE from pokemon")
    fun deleteAll()
}
