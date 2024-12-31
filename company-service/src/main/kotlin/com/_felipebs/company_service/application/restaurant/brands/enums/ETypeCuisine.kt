package com._felipebs.company_service.application.restaurant.brands.enums

enum class ETypeCuisine(val value: String) {
    ITALIAN("ITA"),
    JAPANESE("JAP"),
    MEXICAN("MEX"),
    FRENCH("FRE"),
    BRAZILIAN("BRA"),
    VEGETARIAN_VEGAN("VEG"),
    SEAFOOD("SEA"),
    ARABIAN("ARA"),
    INDIAN("IND"),
    OTHERS("OTR");

    companion object {
        fun parseValue(value: String): ETypeCuisine? {
            return entries.find { it.value == value }
        }
    }
}