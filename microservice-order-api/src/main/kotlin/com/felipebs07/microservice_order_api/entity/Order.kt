package com.felipebs07.microservice_order_api.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "order")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "order_date")
    val orderDate: LocalDateTime,

    @Column(name = "status")
    val status: String,

    @Column(name = "total_amount")
    val totalAmount: Int,

    @Column(name = "shipping_address")
    val shippingAddress: String,

    @Column(name = "payment_status")
    val paymentStatus: String,

    @Column(name = "inventory_status")
    val inventoryStatus: String,
) {
}








