package io.github.harryjhin.entity.core

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@Configuration(proxyBeanMethods = false)
class EntityCoreConfiguration
