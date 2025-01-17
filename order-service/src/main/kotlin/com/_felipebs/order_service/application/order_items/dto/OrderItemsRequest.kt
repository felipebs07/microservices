package com._felipebs.order_service.application.order_items.dto

import java.util.*

data class OrderItemsRequest(
    val ordersId : UUID,
    val unitPrice : Double,
    val quantity : Int,
    val obsTextModification: String?,
)