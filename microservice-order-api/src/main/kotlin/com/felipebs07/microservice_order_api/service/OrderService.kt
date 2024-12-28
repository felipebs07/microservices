package com.felipebs07.microservice_order_api.service

import com.felipebs07.microservice_order_api.repository.IOrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
     private val repository : IOrderRepository
) {

}