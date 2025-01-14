package com._felipebs.company_service.infrasctructure.repository.restaurant

import com._felipebs.company_service.infrasctructure.persistence.restaurant.BrandsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IBrandsRepository : JpaRepository<BrandsEntity, Long> {
}