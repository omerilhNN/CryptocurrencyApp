package com.omrilhn.cryptocurrencyapp.di

import com.omrilhn.cryptocurrencyapp.common.Constants
import com.omrilhn.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.omrilhn.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.omrilhn.cryptocurrencyapp.domain.model.Coin
import com.omrilhn.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //All the dependencies will live as long as our application live
object AppModule {
    @Provides
    @Singleton //there is only one single instance
    fun providePaprikaApi():CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CoinPaprikaApi::class.java)
    }
    @Provides
    @Singleton
    fun provideCoinRepository(api:CoinPaprikaApi):CoinRepository{
        return CoinRepositoryImpl(api)
    }
}