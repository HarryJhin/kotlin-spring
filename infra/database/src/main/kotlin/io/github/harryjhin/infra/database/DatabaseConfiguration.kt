package io.github.harryjhin.infra.database

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@Configuration(proxyBeanMethods = false)
class DatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.read-only")
    fun readOnlyDataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Bean
    @ConfigurationProperties(prefix = "datasource.read-only.configuration")
    fun readOnlyDataSource(
        @Qualifier("readOnlyDataSourceProperties") properties: DataSourceProperties,
    ): HikariDataSource = properties.initializeDataSourceBuilder()
        .type(HikariDataSource::class.java)
        .build().apply {
            this.isReadOnly = true
        }

    @Bean
    @ConfigurationProperties(prefix = "datasource.read-write")
    fun readWriteDataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Bean
    @ConfigurationProperties(prefix = "datasource.read-write.configuration")
    fun readWriteDateSource(
        @Qualifier("readWriteDataSourceProperties") readWriteDataSourceProperties: DataSourceProperties,
    ): HikariDataSource = readWriteDataSourceProperties.initializeDataSourceBuilder()
        .type(HikariDataSource::class.java)
        .build().apply {
            this.isReadOnly = false
        }

    @Bean
    @ConditionalOnBean(name = ["readOnlyDataSource", "readWriteDateSource"])
    fun routingDataSource(
        @Qualifier("readOnlyDataSource") readOnlyDataSource: HikariDataSource,
        @Qualifier("readWriteDateSource") readWriteDateSource: HikariDataSource,
    ): RoutingDataSource = RoutingDataSource(
        readOnlyDataSource = readOnlyDataSource,
        readWriteDataSource = readWriteDateSource,
    )

    @Bean
    @Primary
    @ConditionalOnBean(name = ["routingDataSource"])
    fun dataSource(
        @Qualifier("routingDataSource") routingDataSource: RoutingDataSource,
    ): DataSource = LazyConnectionDataSourceProxy(routingDataSource)
}