package com._felipebs.company_service.application.restaurant.brands.infrastructure.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "brands", schema = "restaurant")
class BrandEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private var id : Long,

    @Column(name = "brand_name")
    private var name : String,

    @Column(name = "type_of_cuisine")
    private var typeOfCuisine : String,

    @Column(name = "status")
    private var status : String,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt : LocalDateTime
)