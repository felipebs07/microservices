package com._felipebs.order_service.application.order_items.domain

import java.time.LocalDateTime

class OrderItems(
    val id : Long?,
    val quantity : Int,
    val unitPrice : Double,
    val obsTextModification : String?,
    val createdAt : LocalDateTime,
) {
    fun isValid() : Boolean {
        val quantityValid = quantity > 0;
        val unitPriceValid = unitPrice > 0;

        return quantityValid && unitPriceValid
    }
}