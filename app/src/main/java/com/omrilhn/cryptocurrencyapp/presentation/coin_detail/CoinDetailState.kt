package com.omrilhn.cryptocurrencyapp.presentation.coin_detail

import com.omrilhn.cryptocurrencyapp.domain.model.Coin
import com.omrilhn.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading:Boolean = true,
    val coin:CoinDetail ?= null,
    val error:String = ""
)
