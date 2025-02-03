package io.github.harryjhin.infra.web

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * jackson 및 web 관련 설정을 담당하는 구성 클래스
 */
@Configuration(proxyBeanMethods = false)
class InfraWebConfiguration {

    @Bean
    fun identifiableBaseEntityModule(): Module {
        return SimpleModule().apply {
            addSerializer(IdentifiableBaseEntitySerializer)
        }
    }
}