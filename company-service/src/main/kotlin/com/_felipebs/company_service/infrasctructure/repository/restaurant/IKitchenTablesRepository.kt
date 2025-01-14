package com._felipebs.company_service.infrasctructure.repository.restaurant

import com._felipebs.company_service.infrasctructure.persistence.restaurant.kitchenTables.KitchenTablesEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IKitchenTablesRepository : JpaRepository<KitchenTablesEntity, UUID> {
}