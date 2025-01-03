package com._felipebs.company_service.application.restaurant.brands.controller

import com._felipebs.company_service.application.restaurant.brands.dto.BrandsRequest
import com._felipebs.company_service.application.restaurant.brands.dto.BrandsResponse
import com._felipebs.company_service.application.restaurant.brands.service.BrandsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/restaurants/brands")
class BrandsController(val service: BrandsService) {
    @GetMapping
    fun findAll() : ResponseEntity<List<BrandsResponse>> {
        val registryList = service.findAll().map { BrandsResponse.fromDomain(it) }
        return if (registryList.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(registryList)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : ResponseEntity<BrandsResponse> {
        return service.findById(id)?.let {
            ResponseEntity.ok(BrandsResponse.fromDomain(it))
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody request: BrandsRequest) : ResponseEntity<BrandsResponse> {
        val registry =  BrandsResponse.fromDomain(service.create(request))
        return ResponseEntity.created(URI("/api/restaurants/brands/${registry.id}")).body(registry)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody request: BrandsRequest, @PathVariable id: Long) : ResponseEntity<BrandsResponse> {
        val registry =  BrandsResponse.fromDomain(service.update(request, id))
        return ResponseEntity.ok().body(registry)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<String> {
        return if (service.delete(id)) ResponseEntity.noContent().build() else ResponseEntity.badRequest().body("Error while trying to delete: $id")
    }
}