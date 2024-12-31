package com._felipebs.company_service.shared.enums

enum class EStatus(val value: String) {
    ACTTVE("ACT"),
    INACTTVE("INA");

    companion object {
        fun parseValue(value: String): EStatus? {
            return EStatus.entries.find { it.value == value }
        }
    }
}