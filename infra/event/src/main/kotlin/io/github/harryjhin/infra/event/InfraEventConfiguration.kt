package io.github.harryjhin.infra.event

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class InfraEventConfiguration {

    @Bean
    fun applicationInitializer(
        applicationContext: ApplicationContext,
    ): InitializingBean = InitializingBean {
        Events.setPublisher(applicationContext)
    }
}