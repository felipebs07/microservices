package com._felipebs.company_service.application.restaurant.kitchen_tables.domain

import java.time.LocalDateTime
import java.util.UUID

class KitchenTables (
    val id : UUID?,
    val kitchenId : Long,
    val avaliability : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {
    fun isValid(): Boolean {
        return kitchenId > 0
    }
}