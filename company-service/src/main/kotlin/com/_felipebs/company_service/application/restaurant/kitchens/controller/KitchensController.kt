package com._felipebs.company_service.application.restaurant.kitchens.controller

import com._felipebs.company_service.application.restaurant.kitchens.dto.KitchensRequest
import com._felipebs.company_service.application.restaurant.kitchens.dto.KitchensResponse
import com._felipebs.company_service.application.restaurant.kitchens.service.KitchensService
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
@RequestMapping("/api/restaurants/kitchens")
class KitchensController(
    val kitchensService: KitchensService
) {

    @GetMapping
    fun findAll() : ResponseEntity<List<KitchensResponse>> {
        val kitchensList = kitchensService.findAll().map { KitchensResponse.fromDomain(it) }
        return if (kitchensList.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(kitchensList)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : ResponseEntity<KitchensResponse> {
        return kitchensService.findById(id)?.let {
            ResponseEntity.ok(KitchensResponse.fromDomain(it))
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody request: KitchensRequest) : ResponseEntity<KitchensResponse> {
        val kitchens = kitchensService.create(request)
        val response = KitchensResponse.fromDomain(kitchens)
        return ResponseEntity.created(URI("/api/kitchens/${response.id}")).body(response)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody request: KitchensRequest, @PathVariable id: Long) : ResponseEntity<KitchensResponse> {
        val kitchens = kitchensService.update(request, id)
        val response = KitchensResponse.fromDomain(kitchens)
        return ResponseEntity.ok().body(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Unit> {
        kitchensService.delete(id)
        return ResponseEntity.noContent().build()
    }

}