package com._felipebs.order_service.application.order_items.dto

import com._felipebs.order_service.application.order_items.domain.OrderItems
import java.time.LocalDateTime
import java.util.UUID

data class OrderItemsResponse(
    val id : Long,
    val quantity : Int,
    val unitPrice : Double,
    val obsTextModification : String?,
    val createdAt : LocalDateTime
) {
    companion object {
        fun fromDomain(domain: OrderItems) = OrderItemsResponse(
            id = domain.id!!,
            quantity = domain.quantity,
            unitPrice = domain.unitPrice,
            obsTextModification = domain.obsTextModification,
            createdAt = domain.createdAt
        )
    }
}