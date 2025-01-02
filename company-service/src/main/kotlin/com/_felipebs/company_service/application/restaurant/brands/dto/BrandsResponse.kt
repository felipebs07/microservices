package com._felipebs.company_service.application.restaurant.brands.dto

import com._felipebs.company_service.application.restaurant.brands.domain.Brands
import java.time.LocalDateTime

data class BrandsResponse(
    val id : Long,
    val name : String,
    val typeOfCuisine : String,
    val status : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {
    companion object {
        fun fromDomain(domain: Brands) = BrandsResponse(
            id = domain.id!!,
            name = domain.name,
            typeOfCuisine = domain.typeOfCuisine,
            status = domain.status,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}