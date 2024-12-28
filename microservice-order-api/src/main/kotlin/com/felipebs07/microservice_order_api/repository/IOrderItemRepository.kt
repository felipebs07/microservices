package com.felipebs07.microservice_order_api.repository

import com.felipebs07.microservice_order_api.entity.Order
import org.springframework.data.jpa.repository.JpaRepository


interface IOrderItemRepository : JpaRepository<Order, Long> {
}