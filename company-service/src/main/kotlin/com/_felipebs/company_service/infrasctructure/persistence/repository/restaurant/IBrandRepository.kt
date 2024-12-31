package com._felipebs.company_service.infrasctructure.persistence.repository.restaurant

import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.BrandsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IBrandRepository : JpaRepository<BrandsEntity, Long> {
}