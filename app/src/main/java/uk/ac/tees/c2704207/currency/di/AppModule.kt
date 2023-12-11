package uk.ac.tees.c2704207.currency.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.c2704207.currency.data.remote.currenyAPI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyApi(): currenyAPI {

        val retrofit = Retrofit
            .Builder()
            .baseUrl(currenyAPI.BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(currenyAPI::class.java)


    }
}