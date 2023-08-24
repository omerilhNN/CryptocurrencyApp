package com.omrilhn.cryptocurrencyapp.presentation.coin_list

import com.omrilhn.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading:Boolean = true,
    val coins:List<Coin> = emptyList(),
    val error:String = ""
)
