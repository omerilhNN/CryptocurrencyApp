package com.omrilhn.cryptocurrencyapp.domain.repository

import com.omrilhn.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.omrilhn.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoin(): List<CoinDto>
    suspend fun getCoinById(coinId:String): CoinDetailDto
}