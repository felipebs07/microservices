package com._felipebs.order_service.infrasctructure.repository

import com._felipebs.order_service.infrasctructure.persistence.OrderItemsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IOrderItemsRepository : JpaRepository<OrderItemsEntity, Long> {
}