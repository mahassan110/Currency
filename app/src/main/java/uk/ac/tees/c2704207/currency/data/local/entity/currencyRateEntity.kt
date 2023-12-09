package uk.ac.tees.c2704207.currency.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class currencyRateEntity(

    @PrimaryKey
    val code: String,
    val name: String,
    val rate: Double

)
