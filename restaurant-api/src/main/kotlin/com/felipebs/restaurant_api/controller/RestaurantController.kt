package com.felipebs.restaurant_api.controller

import com.felipebs.restaurant_api.service.RestaurantService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/restaurant/restaurants")
class RestaurantController(private val restaurantService: RestaurantService) {
}