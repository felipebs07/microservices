package com._felipebs.order_service.infrasctructure.repository

import com._felipebs.order_service.infrasctructure.persistence.OrdersEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IOrdersRepository : JpaRepository<OrdersEntity, UUID> {
}