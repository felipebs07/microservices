package com._felipebs.order_service.application.orders.dto

import com._felipebs.order_service.application.orders.domain.Orders
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class OrdersResponse(
    val id : UUID?,
    val kitchenTableId : Long,
    val paymentId : UUID?,
    val totalPrice : BigDecimal,
    val status : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {
    companion object {
        fun fromDomain(domain: Orders) = OrdersResponse(
            id = domain.id!!,
            kitchenTableId = domain.kitchenTableId,
            paymentId = domain.paymentId,
            totalPrice = domain.totalPrice,
            status = domain.status,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}