package io.github.harryjhin.infra.database

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import javax.sql.DataSource

sealed class DataSourceProperties {
    abstract val url: String
    abstract val username: String
    abstract val password: String
    abstract val driverClassName: String
    abstract val isReadOnly: Boolean

    fun toDataSource(): DataSource = DataSourceBuilder.create()
        .url(url)
        .username(username)
        .password(password)
        .driverClassName(driverClassName)
        .type(HikariDataSource::class.java)
        .build()
        .apply { this.isReadOnly = this@DataSourceProperties.isReadOnly }
}

@ConfigurationProperties(prefix = "spring.datasource.read-write")
class ReadWriteDataSourceProperties(
    override val url: String,
    override val username: String,
    override val password: String,
    override val driverClassName: String,
) : DataSourceProperties() {
    override val isReadOnly: Boolean = false
}

@ConfigurationProperties(prefix = "spring.datasource.read-only")
class ReadOnlyDataSourceProperties(
    override val url: String,
    override val username: String,
    override val password: String,
    override val driverClassName: String,
) : DataSourceProperties() {
    override val isReadOnly: Boolean = true
}
