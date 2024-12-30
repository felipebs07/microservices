package com.felipebs.restaurant_api.service

import com.felipebs.restaurant_api.repository.IKitchenRepository
import org.springframework.stereotype.Service

@Service
class KitchenService(
    private val repository: IKitchenRepository
) {

}