package io.github.harryjhin.infra.cache

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration(proxyBeanMethods = false)
class CacheTesterConfiguration {
}
