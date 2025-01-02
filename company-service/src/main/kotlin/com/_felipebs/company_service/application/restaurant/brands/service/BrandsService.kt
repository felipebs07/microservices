package com._felipebs.company_service.application.restaurant.brands.service


import com._felipebs.company_service.application.restaurant.brands.domain.Brands
import com._felipebs.company_service.application.restaurant.brands.dto.BrandsRequest
import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.BrandsEntity
import com._felipebs.company_service.infrasctructure.persistence.repository.restaurant.IBrandsRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class BrandsService(val repository: IBrandsRepository) {
    val log: Logger = LoggerFactory.getLogger(BrandsEntity::class.java)

    fun findAll(): List<Brands> {
        return repository.findAll().map { it.toDomain() }
    }

    fun findById(id : Long): Brands? {
        return repository.findById(id).getOrNull()?.toDomain()
    }

    fun create(request: BrandsRequest) : Brands {
        val brands = Brands(
            id = null,
            name =  request.name,
            typeOfCuisine = request.typeOfCuisine,
            status = request.status,
            createdAt = LocalDateTime.now(),
            updatedAt = null
        )


        if(brands.isValid().not()) {
            throw IllegalArgumentException("Brand is invalid registry!")
        }

        val entity = repository.save(BrandsEntity.fromDomain(brands))
        return entity.toDomain()
    }

    fun update(request: BrandsRequest, id: Long) : Brands {
        val entitySaved = findById(id) ?: throw RuntimeException("No record found with that ID!")

        val brands = Brands(
            id = entitySaved.id,
            name =  request.name,
            typeOfCuisine = request.typeOfCuisine,
            status = request.status,
            createdAt = entitySaved.createdAt,
            updatedAt = LocalDateTime.now()
        )

        if(brands.isValid().not()) {
            throw IllegalArgumentException("Brand is invalid registry!")
        }

        val entity = repository.save(BrandsEntity.fromDomain(brands))
        return entity.toDomain()
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