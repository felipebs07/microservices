package com._felipebs.company_service.infrasctructure.persistence.restaurant

import com._felipebs.company_service.application.restaurant.kitchens.domain.Kitchens
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "kitchens", schema = "restaurant")
class KitchensEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitchen_id")
    private var id : Long?,

    @Column(name = "establishment_id")
    private var establishmentId : Long,

    @Column(name = "kitchen_name")
    private var name : String,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt : LocalDateTime?
) {
    fun toDomain () = Kitchens (
        id = id,
        establishmentId =  establishmentId,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(domain: Kitchens) = KitchensEntity(
            id = domain.id,
            establishmentId = domain.establishmentId,
            name = domain.name,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}