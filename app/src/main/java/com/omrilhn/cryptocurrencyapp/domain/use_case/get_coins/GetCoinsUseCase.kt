package com.omrilhn.cryptocurrencyapp.domain.use_case.get_coins

import com.omrilhn.cryptocurrencyapp.common.Resource
import com.omrilhn.cryptocurrencyapp.data.remote.dto.toCoin
import com.omrilhn.cryptocurrencyapp.domain.model.Coin
import com.omrilhn.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository : CoinRepository
) {
    operator fun invoke() : kotlinx.coroutines.flow.Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map{it.toCoin()} //Map: Convert every element of the list to type that you want
            emit(Resource.Success(coins))

        }catch( e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))

        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}