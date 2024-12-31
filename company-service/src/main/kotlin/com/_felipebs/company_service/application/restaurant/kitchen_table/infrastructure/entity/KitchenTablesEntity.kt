package com._felipebs.company_service.application.restaurant.kitchen_table.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "kitchen_tables", schema = "restaurant")
class KitchenTablesEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitchen_tables_id")
    private var kitchenTablesId : Long,

    @Column(name = "establishment_id")
    private var establishmentId : Long,

    @Column(name = "availability")
    private var avaliability : String,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt : LocalDateTime
)