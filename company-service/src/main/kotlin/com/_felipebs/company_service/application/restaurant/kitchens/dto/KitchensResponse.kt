package com._felipebs.company_service.application.restaurant.kitchens.dto

import com._felipebs.company_service.application.restaurant.kitchens.domain.Kitchens
import java.time.LocalDateTime

data class KitchensResponse(
    val id : Long,
    val establishmentId : Long,
    val name : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {
    companion object {
        fun fromDomain(domain: Kitchens) = KitchensResponse(
            id = domain.id!!,
            establishmentId = domain.establishmentId,
            name = domain.name,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }
}