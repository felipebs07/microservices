package com._felipebs.order_service.infrasctructure.repository

import com._felipebs.order_service.infrasctructure.persistence.OrdersEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface IOrdersCacheRepository : CrudRepository<OrdersEntity, UUID> {
}