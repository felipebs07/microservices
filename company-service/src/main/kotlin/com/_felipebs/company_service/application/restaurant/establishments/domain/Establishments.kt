package com._felipebs.company_service.application.restaurant.establishments.domain

import java.time.LocalDateTime

class Establishments(
    val id : Long?,
    val brandId : Long,
    val name : String,
    val address : String,
    val phoneNumber : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime?
) {
    fun isValid(): Boolean {
        val brandValid = brandId > 0
        val nameValid = name.isNotBlank() && name.length > 3
        val addressValid = address.isNotBlank()
        val phoneNumberValid = phoneNumber.isNotBlank()

        return brandValid && nameValid && addressValid && phoneNumberValid
    }
}