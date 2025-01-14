package com._felipebs.order_service.application.order_items.service


import com._felipebs.order_service.application.order_items.domain.OrderItems
import com._felipebs.order_service.application.order_items.dto.OrderItemsRequest
import com._felipebs.order_service.infrasctructure.persistence.OrderItemsEntity
import com._felipebs.order_service.infrasctructure.repository.IOrderItemsRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class OrderItemsService(val repository: IOrderItemsRepository) {
    val log: Logger = LoggerFactory.getLogger(OrderItemsEntity::class.java)

    fun findAll(): List<OrderItems> {
        return repository.findAll().map { it.toDomain() }
    }

    fun findById(id : Long): OrderItems? {
        return repository.findById(id).getOrNull()?.toDomain();
    }

    fun create(request: OrderItemsRequest) : OrderItems {
        val orderItems = OrderItems(
            id = null,
            quantity =  request.quantity,
            unitPrice = request.unitPrice,
            obsTextModification = request.obsTextModification,
            createdAt = LocalDateTime.now()
        )

        if(orderItems.isValid().not()) {
            throw IllegalArgumentException("Order is invalid registry!")
        }

        val entity = repository.save(OrderItemsEntity.fromDomain(orderItems))
        return entity.toDomain();
    }

    fun update(request: OrderItemsRequest, id: Long) : OrderItems {
        val entitySaved = findById(id) ?: throw RuntimeException("No record found with that ID!")

        val orderItems = OrderItems(
            id = entitySaved.id,
            quantity =  request.quantity,
            unitPrice = request.unitPrice,
            obsTextModification = request.obsTextModification,
            createdAt = LocalDateTime.now()
        )

        if(orderItems.isValid().not()) {
            throw IllegalArgumentException("Order is invalid registry!")
        }

        val entity = repository.save(OrderItemsEntity.fromDomain(orderItems))
        return entity.toDomain();
    }

    fun delete(id: Long) : Boolean {
        return try {
            repository.deleteById(id)
            true
        } catch(e: Exception) {
            log.error("Error while trying to delete: ${e.message}")
            false
        }
    }
}