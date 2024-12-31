package com._felipebs.company_service.infrasctructure.persistence.repository.restaurant

import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.BrandsEntity
import com._felipebs.company_service.infrasctructure.persistence.entity.restaurant.KitchenTablesEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IKitchenTablesRepository : JpaRepository<KitchenTablesEntity, UUID> {
}