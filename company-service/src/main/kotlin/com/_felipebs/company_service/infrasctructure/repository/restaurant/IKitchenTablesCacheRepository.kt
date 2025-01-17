package com._felipebs.company_service.infrasctructure.repository.restaurant

import com._felipebs.company_service.infrasctructure.persistence.restaurant.kitchenTables.KitchenTablesCacheEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface IKitchenTablesCacheRepository : CrudRepository<KitchenTablesCacheEntity, UUID> {
}