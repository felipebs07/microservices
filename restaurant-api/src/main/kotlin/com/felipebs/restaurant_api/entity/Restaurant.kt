package com.felipebs.restaurant_api.entity

import com.felipebs.restaurant_api.dto.EStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "restaurant")
class Restaurant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    val id: Long?,

    @Column(name = "status")
    @Enumerated
    val status: EStatus,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime,
)







