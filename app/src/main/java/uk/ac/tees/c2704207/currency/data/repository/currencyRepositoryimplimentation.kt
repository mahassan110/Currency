package uk.ac.tees.c2704207.currency.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uk.ac.tees.c2704207.currency.data.local.entity.currencyRateDao
import uk.ac.tees.c2704207.currency.data.local.entity.toCurrencyRate
import uk.ac.tees.c2704207.currency.data.remote.currenyAPI
import uk.ac.tees.c2704207.currency.domain.model.currencyRate
import uk.ac.tees.c2704207.currency.domain.model.resource
import uk.ac.tees.c2704207.currency.domain.repository.currencyRepository

class currencyRepositoryimplimentation (

    private val api: currenyAPI,
    private val dao: currencyRateDao

    ): currencyRepository{


    override fun getCurrencyRateList(): Flow<resource<List<currencyRate>>> = flow{

            val localcurrencyRate = getLocalCurrencyRates()
            emit(resource.Success(localcurrencyRate))
        try {

        }catch ()

    }
    private suspend fun getLocalCurrencyRates(): List<currencyRate>{
        return dao.getAllCurrencyRates().map { it.toCurrencyRate() }

    }

    private suspend fun getRemoteCurrencyrates(): List<currencyRate>{

        val response = api.getLatestRates()
        return response

    }

}