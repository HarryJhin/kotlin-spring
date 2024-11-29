package io.github.harryjhin.entity.core

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * ### Annotation 기반 구성
 *
 * Spring Data JPA 리포지토리 지원은 Java 또는 XML 모두를 통해 활성화 할 수 있습니다.
 */
@EnableJpaAuditing
@Configuration(proxyBeanMethods = false)
class EntityConfiguration
