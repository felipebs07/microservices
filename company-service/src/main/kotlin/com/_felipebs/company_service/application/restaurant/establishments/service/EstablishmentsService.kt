package com._felipebs.company_service.application.restaurant.establishments.service


import com._felipebs.company_service.application.restaurant.establishments.domain.Establishments
import com._felipebs.company_service.application.restaurant.establishments.dto.EstablishmentsRequest
import com._felipebs.company_service.infrasctructure.persistence.restaurant.EstablishmentsEntity
import com._felipebs.company_service.infrasctructure.repository.restaurant.IEstablishmentsRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class EstablishmentsService(val repository: IEstablishmentsRepository) {
    val log: Logger = LoggerFactory.getLogger(EstablishmentsEntity::class.java)

    fun findAll(): List<Establishments> {
        return repository.findAll().map { it.toDomain() }
    }

    fun findById(id : Long): Establishments? {
        return repository.findById(id).getOrNull()?.toDomain()
    }

    fun create(request: EstablishmentsRequest) : Establishments {
        val establishments = Establishments(
            id = null,
            brandId =  request.brandId,
            name = request.name,
            address = request.address,
            phoneNumber = request.phoneNumber,
            createdAt = LocalDateTime.now(),
            updatedAt = null
        )


        if(establishments.isValid().not()) {
            throw IllegalArgumentException("Establishment is invalid registry!")
        }

        val entity = repository.save(EstablishmentsEntity.fromDomain(establishments))
        return entity.toDomain()
    }

    fun update(request: EstablishmentsRequest, id: Long) : Establishments {
        val entitySaved = findById(id) ?: throw RuntimeException("No record found with that ID!")

        val establishments = Establishments(
            id = entitySaved.id,
            brandId =  request.brandId,
            name = request.name,
            address = request.address,
            phoneNumber = request.phoneNumber,
            createdAt = entitySaved.createdAt,
            updatedAt = LocalDateTime.now()
        )

        if(establishments.isValid().not()) {
            throw IllegalArgumentException("Establishment is invalid registry!")
        }

        val entity = repository.save(EstablishmentsEntity.fromDomain(establishments))
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