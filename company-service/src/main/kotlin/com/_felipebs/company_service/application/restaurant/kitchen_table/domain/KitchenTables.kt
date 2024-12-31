package com._felipebs.company_service.application.restaurant.kitchen_table.domain

import java.time.LocalDateTime
import java.util.UUID

class KitchenTables (
    val kitchenTablesId : UUID,
    val kitchenId : Long,
    val avaliability : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime
)