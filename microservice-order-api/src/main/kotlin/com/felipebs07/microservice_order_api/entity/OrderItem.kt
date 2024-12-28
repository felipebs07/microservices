package com.felipebs07.microservice_order_api.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "order-items")
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    val id: Long,

    @Column(name = "order_id")
    val orderId: Long,

    @Column(name = "product_id")
    val productId: Long,

    @Column(name = "quantity")
    val quantity: Int,

    @Column(name = "unit_price")
    val unitPrice: BigDecimal,

    @Column(name = "totaPrice")
    val totaPrice: BigDecimal,
) {


}


