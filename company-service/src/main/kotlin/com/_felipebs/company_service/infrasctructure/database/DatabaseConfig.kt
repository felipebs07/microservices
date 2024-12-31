package com._felipebs.company_service.infrasctructure.database

import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
@EnableConfigurationProperties
class DatabaseConfig {

    @Bean
    fun dataSource(): DataSource {
        val dotenv = dotenv()
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.postgresql.Driver")
        dataSourceBuilder.url(dotenv["DB_POSTGRES_URL"])
        dataSourceBuilder.username(dotenv["DB_POSTGRES_USERNAME"])
        dataSourceBuilder.password(dotenv["DB_POSTGRES_PASSWORD"])

        return dataSourceBuilder.build()
    }
}
