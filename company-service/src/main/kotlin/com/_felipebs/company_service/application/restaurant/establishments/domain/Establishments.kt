package com._felipebs.company_service.application.restaurant.establishments.domain

import java.time.LocalDateTime

class Establishments(
    val id : Long,
    val brandId : Long,
    val establishmentName : String,
    val address : String,
    val phoneNumber : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime
)