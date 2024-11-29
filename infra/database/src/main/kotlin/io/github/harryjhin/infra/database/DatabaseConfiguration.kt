package io.github.harryjhin.infra.database

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties(ReadOnlyDataSourceProperties::class, ReadWriteDataSourceProperties::class)
class DatabaseConfiguration {

    @Bean
    fun readOnlyDataSource(
        properties: ReadOnlyDataSourceProperties,
    ): DataSource = properties.toDataSource()

    @Bean
    fun readWriteDateSource(
        properties: ReadWriteDataSourceProperties,
    ): DataSource = properties.toDataSource()

    @Bean
    fun routingDataSource(
        @Qualifier("readOnlyDataSource") readOnlyDataSource: DataSource,
        @Qualifier("readWriteDateSource") readWriteDataSource: DataSource,
    ): DataSource = RoutingDataSource(
        readOnlyDataSource = readOnlyDataSource,
        readWriteDataSource = readWriteDataSource,
    )

    @Bean
    @Primary
    fun dataSource(
        @Qualifier("routingDataSource") routingDataSource: DataSource,
    ): DataSource = LazyConnectionDataSourceProxy(routingDataSource)
}