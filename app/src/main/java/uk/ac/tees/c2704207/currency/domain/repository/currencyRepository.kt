package uk.ac.tees.c2704207.currency.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.c2704207.currency.domain.model.CurrencyRate
import uk.ac.tees.c2704207.currency.domain.model.Resource



interface CurrencyRepository {
    fun getCurrencyRatesList(): Flow<Resource<List<CurrencyRate>>>
}