package com._felipebs.company_service.infrasctructure.persistence.repository.restaurant

import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.BrandsEntity
import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.KitchensEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IKitchensRepository : JpaRepository<KitchensEntity, Long> {
}