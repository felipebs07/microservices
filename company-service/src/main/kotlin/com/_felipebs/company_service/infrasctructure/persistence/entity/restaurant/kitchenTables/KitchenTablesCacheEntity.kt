package com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.kitchenTables

import com._felipebs.company_service.application.restaurant.kitchen_tables.domain.KitchenTables
import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.time.LocalDateTime
import java.util.UUID

@RedisHash

class KitchenTablesCacheEntity (
    @Id
    private var id : UUID?,

    @Indexed
    private var kitchenId : Long,

    private var avaliability : String,

    private var createdAt : LocalDateTime,

    private var updatedAt : LocalDateTime?
) {
    fun toDomain () =  KitchenTables (
        id = id,
        kitchenId =  kitchenId,
        avaliability = avaliability,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(domain: KitchenTablesEntity) = KitchenTablesCacheEntity(
            id = domain.id,
            kitchenId =  domain.kitchenId,
            avaliability = domain.avaliability,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}