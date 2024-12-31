package com._felipebs.company_service.infrasctructure.persistence.repository.restaurant

import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.BrandsEntity
import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.EstablishmentsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IEstablishmentsRepository : JpaRepository<EstablishmentsEntity, Long> {
}