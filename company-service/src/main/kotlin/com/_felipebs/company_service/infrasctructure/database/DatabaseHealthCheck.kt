package com._felipebs.company_service.infrasctructure.database


import com.zaxxer.hikari.HikariDataSource
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled

import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import javax.sql.DataSource

@Component
class DatabaseHealthCheck(private val dataSource: DataSource) {
    private val log : Logger = LoggerFactory.getLogger(DatabaseHealthCheck::class.java)

   @Scheduled(fixedRate = 30000)
   fun checkDatabase() {
       try {
           val connection = dataSource.connection
           val isValid = connection.isValid(3000)

           Gauge.builder("db_connection_valid", isValid) {if (it) 1.0 else 0.0}
               .description("Status connection with database")
               .register(Metrics.globalRegistry)

           val activeConnections = dataSource.let {
               if(it is HikariDataSource) it.hikariPoolMXBean.activeConnections else 0
           }

           Gauge.builder("db_active_connections", activeConnections) { it.toDouble() }
               .description("Number of active connections")
               .register(Metrics.globalRegistry)
           connection.close()
       } catch (e: Exception) {
           log.error("Error while trying verify database: ${e.cause}")
       }
   }
}
