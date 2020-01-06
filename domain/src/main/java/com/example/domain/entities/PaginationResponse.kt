package com.example.domain.entities

import java.io.Serializable

class PaginationResponse(
    var cards: List<PokemonCard>
): Serializable