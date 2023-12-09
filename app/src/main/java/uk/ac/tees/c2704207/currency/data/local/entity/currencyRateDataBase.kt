package uk.ac.tees.c2704207.currency.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(

    entities = [currencyRateEntity::class],
    version = 1

)

abstract class currencyRateDataBase: RoomDatabase() {

    abstract val currencyRateDao: currencyRateDao

}