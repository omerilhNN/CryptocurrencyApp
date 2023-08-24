package com.omrilhn.cryptocurrencyapp.domain.model

data class Coin( // WE WANT TO SHOW THIS DATAS ON THE UI
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
)
