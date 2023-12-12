package uk.ac.tees.c2704207.currency.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uk.ac.tees.c2704207.currency.data.local.entity.currencyRateDao
import uk.ac.tees.c2704207.currency.data.local.entity.toCurrencyRate
import uk.ac.tees.c2704207.currency.data.local.entity.toCurrencyRateEntity
import uk.ac.tees.c2704207.currency.data.remote.DataTransfer.toCurrencyRates
import uk.ac.tees.c2704207.currency.data.remote.currenyAPI
import uk.ac.tees.c2704207.currency.domain.model.CurrencyRate
import uk.ac.tees.c2704207.currency.domain.model.Resource
import uk.ac.tees.c2704207.currency.domain.repository.CurrencyRepository
import java.io.IOException

class currencyRepositoryImplimentation (
    private val api: currenyAPI,
    private val dao: currencyRateDao
): CurrencyRepository {

    override fun getCurrencyRatesList(): Flow<Resource<List<CurrencyRate>>> = flow {
        val localCurrencyRates = getLocalCurrencyRates()
        emit(Resource.Success(localCurrencyRates))

        try {
            val newRates = getRemoteCurrencyRates()
            updateLocalCurrencyRates(newRates)
            emit(Resource.Success(newRates))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = localCurrencyRates
                )
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong! ${e.message}",
                    data = localCurrencyRates
                )
            )
        }

    }

    private suspend fun getLocalCurrencyRates(): List<CurrencyRate> {
        return dao.getAllCurrencyRates().map { it.toCurrencyRate() }
    }

    private suspend fun getRemoteCurrencyRates(): List<CurrencyRate> {
        val response = api.getLatestRates()
        return response.toCurrencyRates()
    }

    private suspend fun updateLocalCurrencyRates(currencyRates: List<CurrencyRate>) {
        dao.upsertAll(currencyRates.map { it.toCurrencyRateEntity() })
    }
}