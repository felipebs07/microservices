package com.felipebs.restaurant_api.repository

import com.felipebs.restaurant_api.entity.Kitchen
import org.springframework.data.jpa.repository.JpaRepository

interface IKitchenRepository : JpaRepository<Kitchen, Long> {
}