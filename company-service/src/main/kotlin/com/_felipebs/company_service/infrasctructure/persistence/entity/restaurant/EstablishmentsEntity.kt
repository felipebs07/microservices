package com._felipebs.company_service.infrasctructure.persistence.entity.restaurant

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "establishments", schema = "restaurant")
class EstablishmentsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "establishment_id")
    private var id : Long,

    @Column(name = "brand_id")
    private var brandId : Long,

    @Column(name = "establishment_name")
    private var establishmentName : String,

    @Column(name = "address")
    private var address : String,

    @Column(name = "phone_number")
    private var phoneNumber : String,

    @Column(name = "created_at")
    private var createdAt : LocalDateTime,

    @Column(name = "updated_at")
    private var updatedAt : LocalDateTime
)