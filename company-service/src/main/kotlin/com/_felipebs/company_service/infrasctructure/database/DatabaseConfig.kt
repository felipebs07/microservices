package com._felipebs.company_service.infrasctructure.database

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import liquibase.integration.spring.SpringLiquibase
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
        dataSourceBuilder.url(dotenv["COMPANY_SPRING_DATASOURCE_URL"])
        dataSourceBuilder.username(dotenv["COMPANY_POSTGRES_USER"])
        dataSourceBuilder.password(dotenv["COMPANY_POSTGRES_PASSWORD"])

        return dataSourceBuilder.build()
    }

    @Bean
    fun liquibase(dataSource: DataSource): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.defaultSchema = "public"
        liquibase.changeLog =  "classpath:db/changelog/master.xml"
        return liquibase
    }


    private fun dotenv(): Dotenv {
        return Dotenv.configure()
            .filename(".env")
            .load()
    }
}
