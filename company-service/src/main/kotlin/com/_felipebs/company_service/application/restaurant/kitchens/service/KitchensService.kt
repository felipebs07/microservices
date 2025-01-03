package com._felipebs.company_service.application.restaurant.kitchens.service

import com._felipebs.company_service.application.restaurant.kitchens.domain.Kitchens
import com._felipebs.company_service.application.restaurant.kitchens.dto.KitchensRequest
import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.KitchensEntity
import com._felipebs.company_service.infrasctructure.persistence.repository.restaurant.IKitchensRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class KitchensService(val repository: IKitchensRepository) {
    val log: Logger = LoggerFactory.getLogger(KitchensEntity::class.java)

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
            name = request.name,
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
            name = request.name,
            createdAt = entitySaved.createdAt,
            updatedAt = LocalDateTime.now()
        )

        if(kitchens.isValid().not()) {
            throw IllegalArgumentException("Kitchen is invalid registry!")
        }

        val entity = repository.save(KitchensEntity.fromDomain(kitchens))
        return entity.toDomain();
    }

    fun delete(id: Long) : Boolean {
        return try {
            repository.deleteById(id)
            true
        } catch(e: Exception) {
            log.error("Error while trying to delete: ${e.message}")
            false
        }
    }
}