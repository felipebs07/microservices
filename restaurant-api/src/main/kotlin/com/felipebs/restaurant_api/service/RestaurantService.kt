package com.felipebs.restaurant_api.service

import com.felipebs.restaurant_api.repository.IRestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    private val repository: IRestaurantRepository
) {

}