package com.felipebs.restaurant_api.controller

import com.felipebs.restaurant_api.service.KitchenService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/restaurant/kitchens")
class KitchenController(private val kitchenService: KitchenService) {
}