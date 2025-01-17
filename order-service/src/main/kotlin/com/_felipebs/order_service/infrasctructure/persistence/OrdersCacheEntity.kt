package com._felipebs.order_service.infrasctructure.persistence

import com._felipebs.order_service.application.orders.domain.Orders
import jakarta.persistence.*
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@RedisHash
class OrdersCacheEntity (

    @Id
    private var id : UUID?,

    @Indexed
    private var kitchenTableId : Long,

    @Indexed
    private var paymentId : UUID?,

    private var totalPrice : BigDecimal,
    private var status : String,
    private var createdAt : LocalDateTime,
    private var updatedAt : LocalDateTime?
) {
    fun toDomain () = Orders (
        id = id,
        kitchenTableId =  kitchenTableId,
        paymentId = paymentId,
        totalPrice = totalPrice,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(domain: OrdersEntity) = OrdersCacheEntity(
            id = domain.id,
            kitchenTableId =  domain.kitchenTableId,
            paymentId = domain.paymentId,
            totalPrice = domain.totalPrice,
            status = domain.status,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}