package com.example.data.realm.entities

import com.example.domain.entities.Entity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmEntity(
    @PrimaryKey
    var id: Int = 0
) : RealmObject() {

    fun toDomain(): Entity {
        return Entity(
            id = id
        )
    }


    companion object {
        fun toRealm(domain: Entity): RealmEntity {
            return RealmEntity(
                id = domain.id
            )
        }
    }
}