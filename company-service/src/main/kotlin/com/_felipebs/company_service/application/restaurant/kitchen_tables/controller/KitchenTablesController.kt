package com._felipebs.company_service.application.restaurant.kitchen_tables.controller

import com._felipebs.company_service.application.restaurant.kitchen_tables.dto.KitchenTablesRequest
import com._felipebs.company_service.application.restaurant.kitchen_tables.dto.KitchenTablesResponse
import com._felipebs.company_service.application.restaurant.kitchen_tables.service.KitchenTablesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/restaurants/kitchenTables")
class KitchenTablesController(val service: KitchenTablesService) {

    @GetMapping
    fun findAll() : ResponseEntity<List<KitchenTablesResponse>> {
        val registryList = service.findAll().map { KitchenTablesResponse.fromDomain(it) }
        return if (registryList.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(registryList)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID) : ResponseEntity<KitchenTablesResponse> {
        return service.findById(id)?.let {
            ResponseEntity.ok(KitchenTablesResponse.fromDomain(it))
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody request: KitchenTablesRequest) : ResponseEntity<KitchenTablesResponse> {
        val registry =  KitchenTablesResponse.fromDomain(service.create(request))
        return ResponseEntity.created(URI("/api/restaurants/kitchenTables/${registry.id}")).body(registry)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody request: KitchenTablesRequest, @PathVariable id: UUID) : ResponseEntity<KitchenTablesResponse> {
        val registry =  KitchenTablesResponse.fromDomain(service.update(request, id))
        return ResponseEntity.ok().body(registry)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) : ResponseEntity<String> {
        return if (service.delete(id)) ResponseEntity.noContent().build() else ResponseEntity.badRequest().body("Error while trying to delete: $id")
    }
}