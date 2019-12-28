package com.example.data.retrofit

import com.example.domain.entities.PaginationResponse
import com.example.domain.entities.PokemonCard
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("cards")
    fun getPokemonCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("name") name: String
    ): Single<PaginationResponse?>

    @GET("cards")
    fun getCard(
        @Query("id") id: String
    ): Single<PaginationResponse?>

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }
}