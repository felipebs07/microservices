package com._felipebs.company_service.application.restaurant.establishments.controller

import com._felipebs.company_service.application.restaurant.establishments.dto.EstablishmentsRequest
import com._felipebs.company_service.application.restaurant.establishments.dto.EstablishmentsResponse
import com._felipebs.company_service.application.restaurant.establishments.service.EstablishmentsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/restaurants/establishments")
class EstablishmentsController(val service: EstablishmentsService) {
    @GetMapping
    fun findAll() : ResponseEntity<List<EstablishmentsResponse>> {
        val registryList = service.findAll().map { EstablishmentsResponse.fromDomain(it) }
        return if (registryList.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(registryList)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : ResponseEntity<EstablishmentsResponse> {
        return service.findById(id)?.let {
            ResponseEntity.ok(EstablishmentsResponse.fromDomain(it))
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody request: EstablishmentsRequest) : ResponseEntity<EstablishmentsResponse> {
        val registry = EstablishmentsResponse.fromDomain(service.create(request))
        return ResponseEntity.created(URI("/api/restaurants/establishments/${registry.id}")).body(registry)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody request: EstablishmentsRequest, @PathVariable id: Long) : ResponseEntity<EstablishmentsResponse> {
        val registry = EstablishmentsResponse.fromDomain(service.update(request, id))
        return ResponseEntity.ok().body(registry)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<String> {
        return if (service.delete(id)) ResponseEntity.noContent().build() else ResponseEntity.badRequest().body("Error while trying to delete: $id")
    }
}