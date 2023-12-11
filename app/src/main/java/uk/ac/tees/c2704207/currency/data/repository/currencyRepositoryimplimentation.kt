package uk.ac.tees.c2704207.currency.data.repository

import android.content.res.Resources
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uk.ac.tees.c2704207.currency.data.local.entity.currencyRateDao
import uk.ac.tees.c2704207.currency.data.local.entity.toCurrencyRate
import uk.ac.tees.c2704207.currency.data.local.entity.toCurrencyRateEntity
import uk.ac.tees.c2704207.currency.data.remote.DataTransfer.toCurrencyRates
import uk.ac.tees.c2704207.currency.data.remote.DataTransfer.toCurrencyRates
import uk.ac.tees.c2704207.currency.data.remote.currenyAPI
import uk.ac.tees.c2704207.currency.domain.model.CurrencyRate
import uk.ac.tees.c2704207.currency.domain.model.resource
import uk.ac.tees.c2704207.currency.domain.repository.currencyRepository
import java.io.IOException

class currencyRepositoryimplimentation (

    private val api: currenyAPI,
    private val dao: currencyRateDao

    ): currencyRepository{




    override fun getCurrencyRateList(): Flow<resource<List<CurrencyRate>>> = flow{

            val localcurrencyRate = getLocalCurrencyRates()
            emit(resource.Success(localcurrencyRate))
        try {
            val newRates = getRemoteCurrencyrates()
            updateLocalCurrencyRate(newRates)
            emit(resource.Success(newRates))

        }catch (e: IOException){

            emit(

                resource.Error(

                    message = "Server Not Found, Kindly check your internet connection",
                    data = localcurrencyRate

                )

            )

        }catch (e: Exception){

            emit(

                resource.Error(

                    message = "Something went wrong!!!!",
                    data = localcurrencyRate

                )

            )

        }

    }
    private suspend fun getLocalCurrencyRates(): List<CurrencyRate>{
        return dao.getAllCurrencyRates().map { it.toCurrencyRate() }

    }

    private suspend fun getRemoteCurrencyrates(): List<CurrencyRate>{

        val response = api.getLatestRates()
        return response.toCurrencyRates()
    }

    private suspend fun updateLocalCurrencyRate(currencyRates: List<CurrencyRate> ){

        dao.upsertAll(currencyRates.map { it.toCurrencyRateEntity() })

    }

}