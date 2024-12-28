package com.felipebs07.microservice_order_api.controller

import com.felipebs07.microservice_order_api.service.OrderService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/order")
class OrderController(private val orderService: OrderService) {
}