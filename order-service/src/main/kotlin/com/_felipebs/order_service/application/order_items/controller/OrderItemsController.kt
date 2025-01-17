package com._felipebs.order_service.application.order_items.controller

import com._felipebs.order_service.application.order_items.dto.OrderItemsRequest
import com._felipebs.order_service.application.order_items.dto.OrderItemsResponse
import com._felipebs.order_service.application.order_items.service.OrderItemsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/restaurants/orderItems")
class OrderItemsController(
    val service: OrderItemsService
) {

    @GetMapping
    fun findAll() : ResponseEntity<List<OrderItemsResponse>> {
        val registryList = service.findAll().map { OrderItemsResponse.fromDomain(it) }
        return if (registryList.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(registryList)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : ResponseEntity<OrderItemsResponse> {
        return service.findById(id)?.let {
            ResponseEntity.ok(OrderItemsResponse.fromDomain(it))
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody request: OrderItemsRequest) : ResponseEntity<OrderItemsResponse> {
        val registry = OrderItemsResponse.fromDomain(service.create(request))
        return ResponseEntity.created(URI("/api/restaurants/orderItems/${registry.id}")).body(registry)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody request: OrderItemsRequest, @PathVariable id: Long) : ResponseEntity<OrderItemsResponse> {
        val registry = OrderItemsResponse.fromDomain(service.update(request, id))
        return ResponseEntity.ok().body(registry)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<String> {
        return if (service.delete(id)) ResponseEntity.noContent().build() else ResponseEntity.badRequest().body("Error while trying to delete: $id")
    }

}