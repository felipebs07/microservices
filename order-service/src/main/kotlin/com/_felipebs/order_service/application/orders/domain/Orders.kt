package com._felipebs.order_service.application.orders.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class Orders (
    val id : UUID?,
    val kitchenTableId : Long,
    val paymentId : UUID?,
    val totalPrice : BigDecimal,
    val status : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {
    fun isValid(): Boolean {
        val kitchenTableValid = kitchenTableId > 0
        val totalPriceValid = totalPrice > BigDecimal.ZERO

        return kitchenTableValid && totalPriceValid
    }
}