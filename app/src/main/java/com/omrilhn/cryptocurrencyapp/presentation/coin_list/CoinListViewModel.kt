package com.omrilhn.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omrilhn.cryptocurrencyapp.common.Resource
import com.omrilhn.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
):ViewModel(){
    private val _state = mutableStateOf(CoinListState())
    val state : State<CoinListState> = _state

    init{ //EXECUTE THIS FUNCTION
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach{result-> //on Each element that this flow emits
                when(result){
                    is Resource.Success ->{
                        _state.value = CoinListState(coins = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = CoinListState(error = result.message ?:
                        "An unexpected error occured") //when its null "?: "
                        }
                    is Resource.Loading -> {
                        _state.value = CoinListState(isLoading = true)
                    }
                }
        }.launchIn(viewModelScope) // You need to launch it into the viewmodelscope in order to see that it works
    }
}