package com.example.data.caching.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entities.Attack
import com.example.domain.entities.Weakness

@Entity(tableName = "pokemon")
class PokemonCardRoomEntity(
    @PrimaryKey
    var id: String = "",
    var attacks: List<Attack?>? = null,
    var hp: String? = null,
    var imageUrl: String? = null,
    var imageUrlHiRes: String? = null,
    var name: String? = null,
    var nationalPokedexNumber: Int? = null,
    var number: String? = null,
    var rarity: String? = null,
    var series: String? = null,
    var setCode: String? = null,
    var subtype: String? = null,
    var supertype: String? = null,
//    var text: List<String?>? = null,
    var types: List<String?>? = null,
    var weaknesses: List<Weakness>? = null,
    var isFavorite: Int = 0
)