package com._felipebs.company_service.application.restaurant.brands.dto


data class BrandsRequest(
    val name : String,
    val typeOfCuisine : String,
    val status : String
)