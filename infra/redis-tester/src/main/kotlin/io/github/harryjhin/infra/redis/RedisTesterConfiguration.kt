package io.github.harryjhin.infra.redis

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer

@Configuration(proxyBeanMethods = false)
class RedisTesterConfiguration {

    @Bean
    fun redisServer(): RedisServer {
        return RedisServer(6379)
    }

    @PostConstruct
    fun startRedis(
        redisServer: RedisServer
    ) {
        redisServer.start()
    }

    @PreDestroy
    fun stopRedis(
        redisServer: RedisServer
    ) {
        redisServer.stop()
    }
}
