package com.example.data.realm.gateways

import com.example.data.realm.entities.RealmEntity
import com.example.domain.entities.Entity
import com.example.domain.gateways.CacheGateway
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.realm.Realm

class RealmCacheGateway(
    private val realm: Realm,
    private val realmScheduler: Scheduler
) : CacheGateway {

    override fun save(entity: List<Entity>) =
        Completable.defer {
            realm.executeTransaction { realm ->
                val toRealm = entity.map { entity ->
                    RealmEntity(
                        id = entity.id
                    )
                }
                realm.copyToRealmOrUpdate(toRealm)
            }
            Completable.complete()
        }.subscribeOn(realmScheduler)

    override fun get() =
        Single.defer<List<Entity>> {
            var toDomain = listOf<Entity>()
            realm.executeTransaction { realm ->
                val entity = realm.where(RealmEntity::class.java).findAll()

                toDomain = entity.map { it.toDomain() }
            }
            Single.just(toDomain)
        }
}