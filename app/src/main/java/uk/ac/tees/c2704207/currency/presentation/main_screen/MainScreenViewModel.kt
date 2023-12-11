package uk.ac.tees.c2704207.currency.presentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import uk.ac.tees.c2704207.currency.domain.repository.currencyRepository

class MainScreenViewModelViewModel(

    private val repository: currencyRepository

): ViewModel() {

    val state by mutableStateOf(MainScreenState())

    fun onEvent(events: MainScreenEvents){

        when(events){

            is MainScreenEvents.BottomSheetItemClicked -> TODO()
            MainScreenEvents.FromCurrencySelect -> TODO()
            is MainScreenEvents.NumberButtomClicked -> TODO()
            MainScreenEvents.SwapIconClicked -> TODO()
            MainScreenEvents.ToCurrencySelect -> TODO()

        }

    }

}