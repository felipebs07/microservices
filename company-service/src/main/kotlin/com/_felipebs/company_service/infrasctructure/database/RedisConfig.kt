package com._felipebs.company_service.infrasctructure.database

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
@EnableConfigurationProperties
class RedisConfig {
    private val dotenv = dotenv()

    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(com.fasterxml.jackson.datatype.jsr310.JavaTimeModule()) // Suporte para tipos Java 8
            .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // Usa ISO-8601
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {

        return LettuceConnectionFactory(
            RedisStandaloneConfiguration().apply {
                hostName = dotenv["COMPANY_REDIS_CACHE_URL"]
                port = (dotenv["COMPANY_REDIS_CACHE_PORT"]).toInt()
                password = RedisPassword.of(dotenv["COMPANY_REDIS_CACHE_PASSWORD"])
            })
    }

    @Bean
    fun cacheManager(redisConnectionFactory: RedisConnectionFactory, objectMapper: ObjectMapper): CacheManager {
        val config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(120))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer())
            )
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer(objectMapper)
                )
            )

        return RedisCacheManager
            .builder(redisConnectionFactory)
            .cacheDefaults(config)
            .build()
    }


    private fun dotenv(): Dotenv {
        return Dotenv.configure()
            .filename(".env")
            .load()
    }
}
