package com.example.domain.gateways

import com.example.domain.entities.Entity
import io.reactivex.Completable
import io.reactivex.Single

interface CacheGateway {

    fun save(entity: List<Entity>): Completable

    fun get(): Single<List<Entity>>
}