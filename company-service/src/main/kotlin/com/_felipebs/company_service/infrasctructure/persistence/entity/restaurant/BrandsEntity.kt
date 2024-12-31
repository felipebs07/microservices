package com._felipebs.company_service.infrasctructure.persistence.entity.restaurant

import com._felipebs.company_service.application.restaurant.kitchens.domain.Kitchens
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "brands", schema = "restaurant")
class BrandsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    var id : Long,

    @Column(name = "brand_name")
    var name : String,

    @Column(name = "type_of_cuisine")
    var typeOfCuisine : String,

    @Column(name = "status")
    var status : String,

    @Column(name = "created_at")
    var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt : LocalDateTime
) {

}