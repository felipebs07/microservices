package com._felipebs.company_service.application.restaurant.kitchens.infrastructure.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "kitchens", schema = "restaurant")
class KitchensEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitchen_id")
    private var establishmentId : Long,

    @Column(name = "establishment_id")
    private var brandId : Long,

    @Column(name = "kitchen_name")
    private var establishmentName : String,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt : LocalDateTime
)