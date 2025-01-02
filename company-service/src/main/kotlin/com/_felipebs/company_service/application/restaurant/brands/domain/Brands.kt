package com._felipebs.company_service.application.restaurant.brands.domain

import java.time.LocalDateTime

data class Brands(
    val id: Long?,
    val name: String,
    val typeOfCuisine: String,
    val status: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
) {

    fun isValid() : Boolean {
        val nameValid = name.isNotBlank() && name.length > 3
        return nameValid
    }
}