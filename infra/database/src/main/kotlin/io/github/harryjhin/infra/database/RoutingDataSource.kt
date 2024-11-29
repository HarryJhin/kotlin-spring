package io.github.harryjhin.infra.database

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource

class RoutingDataSource(
    private val readOnlyDataSource: DataSource,
    private val readWriteDataSource: DataSource,
) : AbstractRoutingDataSource() {

    init {
        setTargetDataSources(
            mapOf(
                "readWrite" to readWriteDataSource,
                "readOnly" to readOnlyDataSource,
            )
        )
        setDefaultTargetDataSource(readWriteDataSource)
    }

    override fun determineCurrentLookupKey() = when (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
        true -> readOnlyDataSource
        false -> readWriteDataSource
    }
}