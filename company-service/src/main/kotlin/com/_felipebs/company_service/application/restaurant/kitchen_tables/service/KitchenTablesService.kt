package com._felipebs.company_service.application.restaurant.kitchen_tables.service


import com._felipebs.company_service.application.restaurant.kitchen_tables.domain.KitchenTables
import com._felipebs.company_service.application.restaurant.kitchen_tables.dto.KitchenTablesRequest
import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.KitchenTablesEntity
import com._felipebs.company_service.infrasctructure.persistence.repository.restaurant.IKitchenTablesRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class KitchenTablesService (val repository: IKitchenTablesRepository) {
    val log: Logger = LoggerFactory.getLogger(KitchenTablesEntity::class.java)

    fun findAll(): List<KitchenTables> {
        return repository.findAll().map { it.toDomain() }
    }

    fun findById(id : UUID): KitchenTables? {
        return repository.findById(id).getOrNull()?.toDomain()
    }

    fun create(request: KitchenTablesRequest) : KitchenTables {
        val registry = KitchenTables(
            id = null,
            kitchenId =  request.kitchenId,
            avaliability = request.avaliability,
            createdAt = LocalDateTime.now(),
            updatedAt = null
        )


        if(registry.isValid().not()) {
            throw IllegalArgumentException("Kitchen Table is invalid registry!")
        }

        val entity = repository.save(KitchenTablesEntity.fromDomain(registry))
        return entity.toDomain()
    }

    fun update(request: KitchenTablesRequest, id: UUID) : KitchenTables {
        val entitySaved = findById(id) ?: throw RuntimeException("No record found with that ID!")

        val registry = KitchenTables(
            id = entitySaved.id,
            kitchenId =  request.kitchenId,
            avaliability = request.avaliability,
            createdAt = entitySaved.createdAt,
            updatedAt = LocalDateTime.now()
        )

        if(registry.isValid().not()) {
            throw IllegalArgumentException("Kitchen Table is invalid registry!")
        }

        val entity = repository.save(KitchenTablesEntity.fromDomain(registry))
        return entity.toDomain()
    }

    fun delete(id: UUID) : Boolean {
        return try {
            repository.deleteById(id)
            true
        } catch(e: Exception) {
            log.error("Error while trying to delete: ${e.message}")
            false
        }
    }
}