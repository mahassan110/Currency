package uk.ac.tees.c2704207.currency.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.c2704207.currency.data.remote.DataTransfer.CurrencyDataTransfer

interface currenyAPI{

    @GET("v1/latest")
        suspend fun getLatestRates(

            @Query("apikey")  apikey : String = API_KEY

        ) : CurrencyDataTransfer
        companion object{

            const val API_KEY = "fca_live_du3jMyPEMDmzIROBMmv9SG33heIQY5uJzaTfuRSk"
            const val BASE_API = "https://api.freecurrencyapi.com/"

        }
}