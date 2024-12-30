package com.felipebs.restaurant_api.entity

import com.felipebs.restaurant_api.dto.EStatus
import jakarta.persistence.*

@Entity
@Table(name = "restaurant")
class RestaurantKitchen(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_kitchen_id")
    val id: Long?,

    @Column(name = "kitchen_id")
    val kitchenId: Long,

    @Column(name = "restaurant_id")
    val restaurant: Long?,

    @Column(name = "status")
    @Enumerated
    val status: EStatus
)
