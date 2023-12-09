package uk.ac.tees.c2704207.currency.data.local.entity

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface currencyRateDao {

    @Upsert
    suspend fun upsertAll(CurrencyRates: List<currencyRateEntity>)

    @Query("Select * FROM currencyRateEntity")
    suspend fun getAllCurrencyRates(): List<currencyRateEntity>

}