package com.felipebs07.saga_orchestrator_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SagaOrchestratorServiceApplication

fun main(args: Array<String>) {
	runApplication<SagaOrchestratorServiceApplication>(*args)
}
