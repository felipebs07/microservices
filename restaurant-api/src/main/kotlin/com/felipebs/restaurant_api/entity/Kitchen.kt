package com.felipebs.restaurant_api.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "kitchen")
class Kitchen(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitchen_id")
    val id: Long?,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime,
)


