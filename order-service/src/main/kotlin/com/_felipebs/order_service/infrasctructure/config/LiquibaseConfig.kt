package com._felipebs.order_service.infrasctructure.config

import liquibase.integration.spring.SpringLiquibase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class LiquibaseConfig {

    @Bean
    fun liquibase(dataSource: DataSource): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.defaultSchema = "public"
        liquibase.changeLog =  "classpath:db/changelog/master.xml"
        return liquibase
    }
}