package com._felipebs.company_service.infrasctructure.persistence.entity.restaurant

import com._felipebs.company_service.application.restaurant.establishments.domain.Establishments
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "establishments", schema = "restaurant")
class EstablishmentsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "establishment_id")
    private var id : Long?,

    @Column(name = "brand_id")
    private var brandId : Long,

    @Column(name = "establishment_name")
    private var name : String,

    @Column(name = "address")
    private var address : String,

    @Column(name = "phone_number")
    private var phoneNumber : String,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt : LocalDateTime?
) {
    fun toDomain () = Establishments (
        id = id,
        brandId =  brandId,
        name = name,
        address = address,
        phoneNumber = phoneNumber,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(domain: Establishments) = EstablishmentsEntity(
            id = domain.id,
            brandId =  domain.brandId,
            name = domain.name,
            address = domain.address,
            phoneNumber = domain.phoneNumber,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}