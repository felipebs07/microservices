package com._felipebs.order_service.application.orders.enums

enum class EStatus(val value: String) {
    PENDING("PDG"),
    PAID("PID"),
    FINISHED("FSD"),
    ERROR("ERR")
}