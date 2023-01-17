package com.ivy.core.persistence.algorithm

import androidx.room.Dao
import androidx.room.Query
import com.ivy.data.CurrencyCode
import com.ivy.data.DELETING
import kotlinx.coroutines.flow.Flow

@Dao
interface RatesDao {
    @Query("SELECT rate, currency FROM exchange_rates WHERE baseCurrency = :baseCurrency")
    fun findAll(baseCurrency: CurrencyCode): Flow<Rate>

    @Query(
        "SELECT rate, currency FROM exchange_rates_override WHERE baseCurrency = :baseCurrency" +
                " AND sync != $DELETING"
    )
    fun findAllOverrides(baseCurrency: CurrencyCode): Flow<Rate>
}