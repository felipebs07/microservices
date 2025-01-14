package com._felipebs.order_service.infrasctructure.persistence

import com._felipebs.order_service.application.orders.domain.Orders
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "orders", schema = "restaurant")
class OrdersEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "orders_id")
    private var id : UUID?,

    @Column(name = "kitchen_table_id")
    private var kitchenTableId : Long,

    @Column(name = "payment_id")
    private var paymentId : UUID?,

    @Column(name = "total_price")
    private var totalPrice : BigDecimal,

    @Column(name = "status")
    private var status : String,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    @Column(name = "updated_at")
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
        fun fromDomain(domain: Orders) = OrdersEntity(
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