package com._felipebs.company_service.application.restaurant.establishments.dto


data class EstablishmentsRequest(
    val brandId : Long,
    val name : String,
    val address : String,
    val phoneNumber : String,
)