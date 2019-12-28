package com.example.domain.entities

import com.google.gson.annotations.Expose
import java.io.Serializable

class PokemonCard(
    @Expose(serialize = false, deserialize = false)
    var ability: Ability? = null,
    var artist: String? = null,
    var attacks: List<Attack?>? = null,
    var convertedRetreatCost: Int? = null,
    var evolvesFrom: String? = null,
    var hp: String? = null,
    var id: String = "",
    var imageUrl: String? = null,
    var imageUrlHiRes: String? = null,
    var name: String? = null,
    var nationalPokedexNumber: Int? = null,
    var number: String? = null,
    var rarity: String? = null,
    var resistances: List<Resistance>? = null,
    var retreatCost: List<String>? = null,
    var series: String? = null,
    var set: String? = null,
    var setCode: String? = null,
    var subtype: String? = null,
    var supertype: String? = null,
    var text: List<String?>? = null,
    var types: List<String?>? = null,
    var weaknesses: List<Weakness>? = null,
    var isFavorite: Int = 0
) : Serializable