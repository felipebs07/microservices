package com._felipebs.company_service.application.restaurant.kitchen_tables.dto

import com._felipebs.company_service.application.restaurant.kitchen_tables.domain.KitchenTables
import java.time.LocalDateTime
import java.util.*

data class KitchenTablesResponse(
    val id : UUID,
    val kitchenId : Long,
    val avaliability : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {
    companion object {
        fun fromDomain(domain: KitchenTables) = KitchenTablesResponse(
            id = domain.id!!,
            kitchenId = domain.kitchenId,
            avaliability = domain.avaliability,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}