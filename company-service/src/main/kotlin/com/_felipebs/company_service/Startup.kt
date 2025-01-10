package com._felipebs.company_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching

class CompanyServiceApplication

fun main(args: Array<String>) {
	runApplication<CompanyServiceApplication>(*args)
}
