package com.omrilhn.cryptocurrencyapp.domain.use_case.get_coin

import com.omrilhn.cryptocurrencyapp.common.Resource
import com.omrilhn.cryptocurrencyapp.data.remote.dto.toCoin
import com.omrilhn.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.omrilhn.cryptocurrencyapp.domain.model.CoinDetail
import com.omrilhn.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository : CoinRepository
) {
    operator fun invoke(coinId:String) : kotlinx.coroutines.flow.Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))

        }catch( e: HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))

        }catch (e:IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}