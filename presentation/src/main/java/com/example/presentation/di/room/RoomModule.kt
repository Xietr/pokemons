package com.example.presentation.di.room

import android.app.Application
import androidx.room.Room
import com.example.data.caching.room.daos.PokemonCardDao
import com.example.data.caching.room.databases.PokemonCardDataBase
import com.example.presentation.di.AppModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [AppModule::class])
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(app: Application): PokemonCardDataBase {
        return Room.databaseBuilder(app, PokemonCardDataBase::class.java, "pokemon-db").build()
    }

    @Singleton
    @Provides
    fun provideRoomDao(dateBase: PokemonCardDataBase): PokemonCardDao {
        return dateBase.pokemonCardDao()
    }
}