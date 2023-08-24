package com.omrilhn.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel : CoinListViewModel = hiltViewModel()
){
    val state = viewModel.state.value
}