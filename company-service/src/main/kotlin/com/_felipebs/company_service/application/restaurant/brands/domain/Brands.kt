package com._felipebs.company_service.application.restaurant.brands.domain


import com._felipebs.company_service.application.restaurant.brands.enums.ETypeCuisine
import com._felipebs.company_service.shared.enums.EStatus
import java.time.LocalDateTime

data class Brands(
    val id : Long?,
    val name : String,
    val typeOfCuisine : ETypeCuisine,
    val status : EStatus,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {

    fun isDateBrandsValid() : Boolean {
        if(updatedAt == null) return true
        return updatedAt.isAfter(createdAt) || updatedAt.isEqual(createdAt)
    }

    fun isActive() : Boolean {
        return status == EStatus.ACTTVE
    }
}