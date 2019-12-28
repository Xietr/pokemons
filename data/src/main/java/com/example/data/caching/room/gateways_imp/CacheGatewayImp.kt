package com.example.data.caching.room.gateways_imp

import com.example.data.caching.room.daos.PokemonCardDao
import com.example.data.caching.room.entities.PokemonCardRoomEntity
import com.example.domain.entities.PokemonCard
import com.example.domain.gateways.CacheGateway
import io.reactivex.Completable
import io.reactivex.Single


class CacheGatewayImp(
    private val pokemonDao: PokemonCardDao
) : CacheGateway {

    override fun getCards(page: Int, pageSize: Int, name: String): Single<List<PokemonCard?>?> {
        return if (name.isEmpty()) {
            pokemonDao.getCards(pageSize, (page - 1) * pageSize).flatMap { list ->
                Single.just(list.map { card ->
                    toPokemonCard(card)
                })
            }
        } else {
            pokemonDao.getCardsByName(pageSize, (page - 1) * pageSize, name).flatMap { list ->
                Single.just(list.map { card ->
                    toPokemonCard(card)
                })
            }
        }
    }

    override fun getCardById(id: String): Single<PokemonCard> {
        return pokemonDao.getCardsById(id).flatMap { Single.just(toPokemonCard(it)) }
    }

    override fun saveCard(pokemonCard: PokemonCard): Completable {
        return pokemonDao.saveCard(toRoomCard(pokemonCard))
    }

    override fun saveCards(cards: List<PokemonCard?>?, isFavourite: Boolean): Completable {
        val array = cards?.map { card -> card?.let { it -> toRoomCard(it) } }
        return pokemonDao.saveCards(array)
    }

    override fun getFavoriteCards(page: Int, pageSize: Int): Single<List<PokemonCard?>?> {
        return pokemonDao.getFavoriteCards(pageSize, (page - 1) * pageSize).flatMap { list ->
            Single.just(list.map { card ->
                toPokemonCard(card)
            })
        }
    }

    override fun updateCard(id: String, isFavourite: Int): Completable {
        return pokemonDao.updateCard(id, isFavourite)
    }


    private fun toRoomCard(pokemonCard: PokemonCard): PokemonCardRoomEntity {
        return pokemonCard.run {
            PokemonCardRoomEntity(
                id = id,
                attacks = attacks,
                hp = hp,
                imageUrl = imageUrl,
                imageUrlHiRes = imageUrlHiRes,
                name = name,
                nationalPokedexNumber = nationalPokedexNumber,
                number = number,
                rarity = rarity,
                series = series,
                setCode = setCode,
                subtype = subtype,
                supertype = supertype,
                text = text,
                types = types,
                weaknesses = weaknesses
            )
        }
    }

    private fun toPokemonCard(pokemonCardRoomEntity: PokemonCardRoomEntity): PokemonCard {
        pokemonCardRoomEntity.run {
            return PokemonCard(
                id = id,
                attacks = attacks,
                hp = hp,
                imageUrl = imageUrl,
                imageUrlHiRes = imageUrlHiRes,
                name = name,
                nationalPokedexNumber = nationalPokedexNumber,
                number = number,
                rarity = rarity,
                series = series,
                setCode = setCode,
                subtype = subtype,
                supertype = supertype,
                text = text,
                types = types,
                weaknesses = weaknesses,
                isFavorite = isFavorite
            )
        }
    }
}