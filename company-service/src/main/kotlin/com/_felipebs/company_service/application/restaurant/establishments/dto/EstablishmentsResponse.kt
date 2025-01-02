package com._felipebs.company_service.application.restaurant.establishments.dto

import com._felipebs.company_service.application.restaurant.kitchen_tables.dto.KitchenTablesResponse
import com._felipebs.company_service.application.restaurant.establishments.domain.Establishments
import java.time.LocalDateTime

data class EstablishmentsResponse(
    val id : Long,
    val brandId : Long,
    val name : String,
    val address : String,
    val phoneNumber : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {
    companion object {
        fun fromDomain(domain: Establishments) = EstablishmentsResponse(
            id = domain.id!!,
            brandId = domain.brandId,
            name = domain.name,
            address = domain.address,
            phoneNumber = domain.phoneNumber,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}