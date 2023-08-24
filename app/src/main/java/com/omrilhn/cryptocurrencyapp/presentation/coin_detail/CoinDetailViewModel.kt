package com.omrilhn.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omrilhn.cryptocurrencyapp.common.Constants
import com.omrilhn.cryptocurrencyapp.common.Resource
import com.omrilhn.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import com.omrilhn.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel(){
    private val _state = mutableStateOf(CoinDetailState())
    val state : State<CoinDetailState> = _state

    init{ //EXECUTE THIS FUNCTION
        //let: check if it is null
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {coinId->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId:String){
        getCoinUseCase(coinId).onEach{result-> //on Each element that this flow emits
                when(result){
                    is Resource.Success ->{
                        _state.value = CoinDetailState(coin = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = CoinDetailState(error = result.message ?:
                        "An unexpected error occured") //when its null "?: "
                        }
                    is Resource.Loading -> {
                        _state.value = CoinDetailState(isLoading = true)
                    }
                }
        }.launchIn(viewModelScope) // You need to launch it into the viewmodelscope in order to see that it works
    }
}