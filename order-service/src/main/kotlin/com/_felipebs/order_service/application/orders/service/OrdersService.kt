package com._felipebs.order_service.application.orders.service


import com._felipebs.order_service.application.orders.domain.Orders
import com._felipebs.order_service.application.orders.dto.OrdersRequest
import com._felipebs.order_service.application.orders.enums.EStatus
import com._felipebs.order_service.infrasctructure.persistence.OrdersCacheEntity

import com._felipebs.order_service.infrasctructure.persistence.OrdersEntity
import com._felipebs.order_service.infrasctructure.repository.IOrdersCacheRepository
import com._felipebs.order_service.infrasctructure.repository.IOrdersRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Service
class OrdersService (val repository: IOrdersRepository, val cacheRepository: IOrdersCacheRepository) {
    val log: Logger = LoggerFactory.getLogger(OrdersEntity::class.java)

    fun findAll(): List<Orders> {
       return  repository.findAll().map { it.toDomain() }
    }

    fun findById(id : UUID): Orders? {
        val cachedEntity = cacheRepository.findById(id).orElse(null)
        if(cachedEntity != null) return cachedEntity.toDomain();

        val dbEntity = repository.findById(id).orElse(null) ?: return null

        val cacheEntity = OrdersCacheEntity.fromDomain(dbEntity)

        return cacheRepository.save(cacheEntity).toDomain()
    }

    @Transactional
    fun create(request: OrdersRequest) : Orders {
        val registry = Orders(
            id = null,
            kitchenTableId =  request.kitchenTableId,
            paymentId = null,
            totalPrice = BigDecimal.ZERO,
            status = EStatus.PENDING.value,
            createdAt = LocalDateTime.now(),
            updatedAt = null
        )

        if(registry.isValid().not()) {
            throw IllegalArgumentException("Order is invalid registry!")
        }

        val entity = repository.save(OrdersEntity.fromDomain(registry))
        val cache = cacheRepository.save(OrdersCacheEntity.fromDomain(entity))
        return cache.toDomain()
    }


    @Transactional
    fun delete(id: UUID) : Boolean {
        return try {
            cacheRepository.deleteById(id)
            repository.deleteById(id)
            true
        } catch(e: Exception) {
            log.error("Error while trying to delete: ${e.message}")
            false
        }
    }
}