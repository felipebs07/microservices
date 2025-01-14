package com._felipebs.order_service.infrasctructure.persistence

import com._felipebs.order_service.application.order_items.domain.OrderItems
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "order_items", schema = "restaurant")
class OrderItemsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private var id : Long?,

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
        quantity =  quantity,
        unitPrice = unitPrice,
        obsTextModification = obsTextModification,
        createdAt = createdAt
    )

    companion object {
        fun fromDomain(domain: OrderItems) = OrderItemsEntity(
            id = domain.id,
            quantity =  domain.quantity,
            unitPrice = domain.unitPrice,
            obsTextModification = domain.obsTextModification,
            createdAt = domain.createdAt
        )
    }
}