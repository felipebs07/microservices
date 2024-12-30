package com.felipebs.restaurant_api.entity

import com.felipebs.restaurant_api.dto.EAvailability
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "kitchen_table")
class KitchenTable(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "kitchen_table_id")
    val id: UUID?,

    @Column(name = "kitchen_id")
    val kitchenId: Long,

    @Column(name = "availability")
    @Enumerated
    val availability: EAvailability,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime,
)


