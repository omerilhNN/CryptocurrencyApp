package com.omrilhn.cryptocurrencyapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.omrilhn.cryptocurrencyapp.domain.model.Coin

data class CoinDto( //WE GET THESE FROM API HOWEVER SHOW Coin Class(which is into the domain)
    val id: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("isNew")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)
fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}