package com._felipebs.order_service.infrasctructure.persistence

import com._felipebs.order_service.application.order_items.domain.OrderItems
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "order_items", schema = "restaurant")
class OrderItemsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id")
    private var id : Long?,

    @Column(name = "orders_id")
    private var orderId : UUID,

    @Column(name = "quantity")
    private var quantity : Int,

    @Column(name = "unit_price")
    private var unitPrice : Double,

    @Column(name = "obs_text_modification")
    private var obsTextModification : String?,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    ) {
    fun toDomain () = OrderItems (
        id = id,
        orderId = orderId,
        quantity =  quantity,
        unitPrice = unitPrice,
        obsTextModification = obsTextModification,
        createdAt = createdAt
    )

    companion object {
        fun fromDomain(domain: OrderItems) = OrderItemsEntity(
            id = domain.id,
            orderId = domain.orderId,
            quantity =  domain.quantity,
            unitPrice = domain.unitPrice,
            obsTextModification = domain.obsTextModification,
            createdAt = domain.createdAt
        )
    }
}