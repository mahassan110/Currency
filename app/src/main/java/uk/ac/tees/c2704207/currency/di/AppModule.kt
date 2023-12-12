package uk.ac.tees.c2704207.currency.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.c2704207.currency.data.local.entity.currencyRateDataBase
import uk.ac.tees.c2704207.currency.data.remote.currenyAPI
import uk.ac.tees.c2704207.currency.data.repository.currencyRepositoryImplimentation
import uk.ac.tees.c2704207.currency.domain.repository.CurrencyRepository
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

    @Provides
    @Singleton
    fun provideDatabase(application: Application): currencyRateDataBase {
        return Room
            .databaseBuilder(
                application,
                currencyRateDataBase::class.java,
                "currency_db"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: currenyAPI,
        db: currencyRateDataBase
    ): CurrencyRepository {
        return currencyRepositoryImplimentation(
            api = api,
            dao = db.currencyRateDao
        )
    }

}