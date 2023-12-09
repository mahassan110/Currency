package uk.ac.tees.c2704207.currency.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.c2704207.currency.domain.model.currencyRate
import uk.ac.tees.c2704207.currency.domain.model.resource

interface currencyRepository {

    fun getCurrencyRateList(): Flow<resource<List<currencyRate>>>

}