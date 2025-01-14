package com._felipebs.order_service.application.order_items.dto

data class OrderItemsRequest(
    val ordersId : Long,
    val unitPrice : Double,
    val quantity : Int,
    val obsTextModification: String?,
)