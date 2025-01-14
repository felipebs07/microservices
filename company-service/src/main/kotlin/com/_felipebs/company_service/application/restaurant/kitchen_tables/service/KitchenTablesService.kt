package com._felipebs.company_service.application.restaurant.kitchen_tables.service


import com._felipebs.company_service.application.restaurant.kitchen_tables.domain.KitchenTables
import com._felipebs.company_service.application.restaurant.kitchen_tables.dto.KitchenTablesRequest
import com._felipebs.company_service.infrasctructure.persistence.restaurant.kitchenTables.KitchenTablesCacheEntity
import com._felipebs.company_service.infrasctructure.persistence.restaurant.kitchenTables.KitchenTablesEntity
import com._felipebs.company_service.infrasctructure.repository.restaurant.IKitchenTablesCacheRepository
import com._felipebs.company_service.infrasctructure.repository.restaurant.IKitchenTablesRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
class KitchenTablesService (val repository: IKitchenTablesRepository, val cacheRepository: IKitchenTablesCacheRepository) {
    val log: Logger = LoggerFactory.getLogger(KitchenTablesEntity::class.java)

    fun findAll(): List<KitchenTables> {
       return  repository.findAll().map { it.toDomain() }
    }


    fun findById(id : UUID): KitchenTables? {
        val cachedEntity = cacheRepository.findById(id).orElse(null)
        if(cachedEntity != null) return cachedEntity.toDomain();

        val dbEntity = repository.findById(id).orElse(null) ?: return null

        val cacheEntity = KitchenTablesCacheEntity.fromDomain(dbEntity)

        return cacheRepository.save(cacheEntity).toDomain()
    }

    @Transactional
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
        val cache = cacheRepository.save(KitchenTablesCacheEntity.fromDomain(entity))
        return cache.toDomain()
    }

    @Transactional
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
        val cache = cacheRepository.save(KitchenTablesCacheEntity.fromDomain(entity))
        return cache.toDomain()
    }

    @Transactional
    fun delete(id: UUID) : Boolean {
        return try {
            cacheRepository.deleteById(id)
            repository.deleteById(id)
            true
        } catch(e: Exception) {
            log.error("Error while trying to delete: ${e.message}")
            false
        }
    }
}