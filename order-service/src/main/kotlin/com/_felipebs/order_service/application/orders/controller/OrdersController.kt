package com._felipebs.order_service.application.orders.controller

import com._felipebs.order_service.application.orders.dto.OrdersRequest
import com._felipebs.order_service.application.orders.dto.OrdersResponse
import com._felipebs.order_service.application.orders.service.OrdersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/restaurants/orders")
class OrdersController(val service: OrdersService) {

    @GetMapping
    fun findAll() : ResponseEntity<List<OrdersResponse>> {
        val registryList = service.findAll().map { OrdersResponse.fromDomain(it) }
        return if (registryList.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(registryList)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID) : ResponseEntity<OrdersResponse> {
        return service.findById(id)?.let {
            ResponseEntity.ok(OrdersResponse.fromDomain(it))
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody request: OrdersRequest) : ResponseEntity<OrdersResponse> {
        val registry =  OrdersResponse.fromDomain(service.create(request))
        return ResponseEntity.created(URI("/api/restaurants/orders/${registry.id}")).body(registry)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody request: OrdersRequest, @PathVariable id: UUID) : ResponseEntity<OrdersResponse> {
        val registry =  OrdersResponse.fromDomain(service.update(request, id))
        return ResponseEntity.ok().body(registry)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) : ResponseEntity<String> {
        return if (service.delete(id)) ResponseEntity.noContent().build() else ResponseEntity.badRequest().body("Error while trying to delete: $id")
    }
}