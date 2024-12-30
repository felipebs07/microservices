package com.felipebs.restaurant_api.repository

import com.felipebs.restaurant_api.entity.Restaurant
import org.springframework.data.jpa.repository.JpaRepository

interface IRestaurantRepository : JpaRepository<Restaurant, Long> {
}