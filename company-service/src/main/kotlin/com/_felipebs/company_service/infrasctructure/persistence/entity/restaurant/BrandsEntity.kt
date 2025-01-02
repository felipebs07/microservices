package com._felipebs.company_service.infrasctructure.persistence.entity.restaurant

import com._felipebs.company_service.application.restaurant.brands.domain.Brands
import com._felipebs.company_service.shared.enums.EStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "brands", schema = "restaurant")
class BrandsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    var id : Long?,

    @Column(name = "brand_name")
    var name : String,

    @Column(name = "type_of_cuisine")
    var typeOfCuisine : String,

    @Column(name = "status")
    var status : String,

    @Column(name = "created_at")
    var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt : LocalDateTime?
) {
    fun toDomain () =  Brands (
        id = id,
        name =  name,
        typeOfCuisine = typeOfCuisine,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(domain: Brands) = BrandsEntity(
            id = domain.id,
            name =  domain.name,
            typeOfCuisine = domain.typeOfCuisine,
            status = domain.status,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}