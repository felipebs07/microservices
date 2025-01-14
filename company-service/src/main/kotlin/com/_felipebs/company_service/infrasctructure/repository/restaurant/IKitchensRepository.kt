package com._felipebs.company_service.infrasctructure.repository.restaurant

import com._felipebs.company_service.infrasctructure.persistence.restaurant.KitchensEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IKitchensRepository : JpaRepository<KitchensEntity, Long> {
}