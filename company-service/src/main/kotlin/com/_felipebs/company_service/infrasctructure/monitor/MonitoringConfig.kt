package com._felipebs.company_service.infrasctructure.monitor


import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Aspect
@Component
class MonitoringConfig {
    private val log : Logger = LoggerFactory.getLogger(MonitoringConfig::class.java)

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    fun monitorEndPoints(joinPoint: ProceedingJoinPoint) : Any {
        val endpoint = extractEndPoint(joinPoint)
        return try {
            val result = joinPoint.proceed()
            val duration = System.currentTimeMillis()

            val counter: Counter = Counter.builder("app_requests_total")
                .description("Request Total per endpoint")
                .tag("endpoint", endpoint)
                .register(Metrics.globalRegistry)
            counter.increment()
            Timer.builder("app_request_duration")
                .tag("endpoint", endpoint)
                .register(Metrics.globalRegistry)
                .record(duration, TimeUnit.MILLISECONDS)
            log.info("Endpoint: ${endpoint}, Duration: ${duration}ms")
            result
        } catch(e: Exception) {
            Counter.builder("app_errors_total")
                .tag("endpoint", endpoint)
                .tag("error", e.javaClass.simpleName)
                .register(Metrics.globalRegistry)
                .increment()
            throw e
        }
    }

    private fun extractEndPoint(joinPoint: ProceedingJoinPoint): String {
        return joinPoint.signature.declaringTypeName + "." + joinPoint.signature.name
    }
}
