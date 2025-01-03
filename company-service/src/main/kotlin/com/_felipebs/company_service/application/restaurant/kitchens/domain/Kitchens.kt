package com._felipebs.company_service.application.restaurant.kitchens.domain

import java.time.LocalDateTime

class Kitchens(
    val id: Long?,
    val establishmentId: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
) {
    fun isValid() : Boolean {
        return name.isNotBlank() && establishmentId > 0 && id?.let { it > 0 } ?: true
    }
}