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
    val service: KitchensService
) {

    @GetMapping
    fun findAll() : ResponseEntity<List<KitchensResponse>> {
        val registryList = service.findAll().map { KitchensResponse.fromDomain(it) }
        return if (registryList.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(registryList)
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : ResponseEntity<KitchensResponse> {
        return service.findById(id)?.let {
            ResponseEntity.ok(KitchensResponse.fromDomain(it))
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody request: KitchensRequest) : ResponseEntity<KitchensResponse> {
        val registry = KitchensResponse.fromDomain(service.create(request))
        return ResponseEntity.created(URI("/api/restaurants/kitchens/${registry.id}")).body(registry)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody request: KitchensRequest, @PathVariable id: Long) : ResponseEntity<KitchensResponse> {
        val registry = KitchensResponse.fromDomain(service.update(request, id))
        return ResponseEntity.ok().body(registry)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<String> {
        return if (service.delete(id)) ResponseEntity.noContent().build() else ResponseEntity.badRequest().body("Error while trying to delete: $id")
    }

}