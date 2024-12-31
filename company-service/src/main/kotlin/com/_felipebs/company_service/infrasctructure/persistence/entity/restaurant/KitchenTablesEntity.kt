package com._felipebs.company_service.infrasctructure.persistence.entity.restaurant

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "kitchen_tables", schema = "restaurant")
class KitchenTablesEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "kitchen_tables_id")
    private var id : UUID,

    @Column(name = "kitchen_id")
    private var kitchenId : Long,

    @Column(name = "availability")
    private var avaliability : String,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt : LocalDateTime
)