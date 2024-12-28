package com.felipebs07.microservice_order_api.controller

import com.felipebs07.microservice_order_api.service.OrderItemService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orderItem")
class OrderItemController(private val orderItemService : OrderItemService){
}