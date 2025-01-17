package com._felipebs.order_service.infrasctructure.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties
class DatabaseConfig {
    private val dotenv = dotenv()

    @Bean
    fun dataSource(): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.postgresql.Driver")
        dataSourceBuilder.url(dotenv["ORDER_SPRING_DATASOURCE_URL"])
        dataSourceBuilder.username(dotenv["ORDER_POSTGRES_USER"])
        dataSourceBuilder.password(dotenv["ORDER_POSTGRES_PASSWORD"])

        return dataSourceBuilder.build()
    }

    private fun dotenv(): Dotenv {
        return Dotenv.configure()
            .filename(".env")
            .load()
    }
}
