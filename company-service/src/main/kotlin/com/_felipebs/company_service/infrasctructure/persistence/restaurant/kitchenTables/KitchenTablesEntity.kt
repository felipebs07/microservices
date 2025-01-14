package com._felipebs.company_service.infrasctructure.persistence.restaurant.kitchenTables

import com._felipebs.company_service.application.restaurant.kitchen_tables.domain.KitchenTables
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
    var id : UUID?,

    @Column(name = "kitchen_id")
    var kitchenId : Long,

    @Column(name = "availability")
    var avaliability : String,

    @Column(name = "created_at")
    var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt : LocalDateTime?
) {

    fun toDomain () =  KitchenTables (
        id = id,
        kitchenId =  kitchenId,
        avaliability = avaliability,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(domain: KitchenTables) = KitchenTablesEntity(
            id = domain.id,
            kitchenId =  domain.kitchenId,
            avaliability = domain.avaliability,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}