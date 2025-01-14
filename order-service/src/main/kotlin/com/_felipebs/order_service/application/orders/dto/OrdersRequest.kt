package com._felipebs.order_service.application.orders.dto

import com._felipebs.order_service.infrasctructure.persistence.OrderItemsEntity
import java.math.BigDecimal


data class OrdersRequest(
    val kitchenTableId : Long,
    val totalPrice : BigDecimal,
    val listItems: List<OrderItemsEntity>
)