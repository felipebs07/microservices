package com.felipebs07.microservice_order_api.service

import com.felipebs07.microservice_order_api.repository.IOrderItemRepository
import org.springframework.stereotype.Service

@Service
class OrderItemService(
     private val repository : IOrderItemRepository
) {

}