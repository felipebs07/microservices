package com.felipebs07.microservice_order_api.repository

import com.felipebs07.microservice_order_api.entity.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface IOrderRepository : JpaRepository<OrderItem, Long> {
}