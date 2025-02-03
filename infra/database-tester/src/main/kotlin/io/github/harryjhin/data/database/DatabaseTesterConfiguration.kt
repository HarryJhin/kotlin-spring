package io.github.harryjhin.data.database

import org.springframework.context.annotation.Configuration
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@Configuration(proxyBeanMethods = false)
class DatabaseTesterConfiguration