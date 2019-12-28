package com.example.domain.entities

data class Attack(
    var convertedEnergyCost: Int? = null,
    var cost: List<String>? = null,
    var damage: String? = null,
    var name: String? = null,
    var text: String? = null
)