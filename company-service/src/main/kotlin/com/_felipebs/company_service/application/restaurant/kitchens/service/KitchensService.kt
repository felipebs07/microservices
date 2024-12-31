package com._felipebs.company_service.application.restaurant.kitchens.service

import com._felipebs.company_service.application.restaurant.kitchens.domain.Kitchens
import com._felipebs.company_service.application.restaurant.kitchens.dto.KitchensRequest
import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.KitchensEntity
import com._felipebs.company_service.infrasctructure.persistence.repository.restaurant.IKitchensRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class KitchensService(val repository: IKitchensRepository) {
    val log = LoggerFactory.getLogger(KitchensEntity::class.java)

    fun findAll(): List<Kitchens> {
        return repository.findAll().map { it.toDomain() }
    }

    fun findById(id : Long): Kitchens? {
        return repository.findById(id).getOrNull()?.toDomain();
    }

    fun create(request: KitchensRequest) : Kitchens {
        val kitchens = Kitchens(
            id = null,
            establishmentId =  request.establishmentId,
            kitchenName = request.kitchenName,
            createdAt = LocalDateTime.now(),
            updatedAt = null
        )

        if(kitchens.isValid().not()) {
            throw IllegalArgumentException("Kitchen is invalid registry!")
        }

        val entity = repository.save(KitchensEntity.fromDomain(kitchens))
        return entity.toDomain();
    }

    fun update(request: KitchensRequest, id: Long) : Kitchens {
        val entitySaved = findById(id) ?: throw RuntimeException("No record found with that ID!")

        val kitchens = Kitchens(
            id = entitySaved.id,
            establishmentId =  request.establishmentId,
            kitchenName = request.kitchenName,
            createdAt = entitySaved.createdAt,
            updatedAt = LocalDateTime.now()
        )

        if(kitchens.isValid().not()) {
            throw IllegalArgumentException("Kitchen is invalid registry!")
        }

        val entity = repository.save(KitchensEntity.fromDomain(kitchens))
        return entity.toDomain();
    }

    fun delete(id: Long) {
        try {
            repository.deleteById(id)
        } catch(e: Exception) {
            log.error("Error while trying to delete: ${e.message}")
        }
    }
}