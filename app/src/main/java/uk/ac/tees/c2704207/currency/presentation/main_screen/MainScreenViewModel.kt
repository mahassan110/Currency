package uk.ac.tees.c2704207.currency.presentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uk.ac.tees.c2704207.currency.domain.model.resource
import uk.ac.tees.c2704207.currency.domain.repository.currencyRepository
import java.text.DecimalFormat

class MainScreenViewModelViewModel(

    private val repository: currencyRepository

): ViewModel() {

    var state by mutableStateOf(MainScreenState())

    init {
        getCurrencyRatesList()
    }

    fun onEvent(events: MainScreenEvents){

        when(events){

            is MainScreenEvents.BottomSheetItemClicked -> {

                updateCurrencyValue(value = events.value)

            }
            MainScreenEvents.FromCurrencySelect -> {

                state = state.copy(selection = SelectionState.FROM)

            }
            is MainScreenEvents.NumberButtomClicked -> {



            }
            MainScreenEvents.SwapIconClicked -> {

                state = state.copy(

                    fromCurrencyCode = state.toCurrencyCode,
                    fromCurrencyValue = state.toCurrencyValue,
                    toCurrencyCode = state.fromCurrencyCode,
                    toCurrencyValue = state.fromCurrencyValue

                )

            }
            MainScreenEvents.ToCurrencySelect -> {

                state = state.copy(selection = SelectionState.TO)

            }

        }

    }
    private fun getCurrencyRatesList() {

        viewModelScope.launch {

            repository
                .getCurrencyRateList()
                .collectLatest { result ->
                    when(result){
                        is resource.Error -> {
                            state = state.copy(

                                currencyRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                error = result.message

                            )

                        }
                        is resource.Success -> {

                            state = state.copy(

                                currencyRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                error = null

                            )

                        }
                    }
                }

        }

    }

        private fun updateCurrencyValue(value: String){

            val currentCurrenyValue = when(state.selection){

                SelectionState.FROM -> state.fromCurrencyValue
                SelectionState.TO -> state.toCurrencyValue

            }

            val fromCurrencyRate = state.currencyRates[state.fromCurrencyValue]?.rate ?: 0.0
            val toCurrenyRate = state.currencyRates[state.toCurrencyValue]?.rate ?: 0.0


            val updatedCurrencyValue = when(value){

                "C" -> "0.00"
                else -> if (currentCurrenyValue == "0.00") value else currentCurrenyValue + value

            }

            val numberFormat = DecimalFormat("#.00")

            when(state.selection){

                SelectionState.FROM->{
                    val toValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                    val fromValue = toValue / toCurrenyRate * fromCurrencyRate
                    state = state.copy(

                        fromCurrencyValue = updatedCurrencyValue,
                        toCurrencyValue = numberFormat.format(fromValue)
                    )
                }
                SelectionState.TO ->{

                }
            }
        }
}