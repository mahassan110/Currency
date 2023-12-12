package uk.ac.tees.c2704207.currency.presentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uk.ac.tees.c2704207.currency.domain.model.Resource
import uk.ac.tees.c2704207.currency.domain.repository.CurrencyRepository
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: CurrencyRepository

): ViewModel() {

    var state by mutableStateOf(MainScreenState())

    init {
        getCurrencyRatesList()
    }

    fun onEvent(event: MainScreenEvents){

        when(event){

            is MainScreenEvents.BottomSheetItemClicked -> {
                if (state.selection == SelectionState.FROM) {
                    state = state.copy(fromCurrencyCode = event.value)
                } else if (state.selection == SelectionState.TO) {
                    state = state.copy(toCurrencyCode = event.value)
                }
                updateCurrencyValue("")
            }
            MainScreenEvents.FromCurrencySelect -> {

                state = state.copy(selection = SelectionState.FROM)

            }
            is MainScreenEvents.NumberButtomClicked -> {

                updateCurrencyValue(value = event.value)

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
                .getCurrencyRatesList()
                .collectLatest { result ->
                    when(result){
                        is Resource.Error -> {
                            state = state.copy(

                                currencyRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                error = result.message

                            )

                        }
                        is Resource.Success -> {

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

            val fromCurrencyRate = state.currencyRates[state.fromCurrencyCode]?.rate ?: 0.0
            val toCurrenyRate = state.currencyRates[state.toCurrencyCode]?.rate ?: 0.0


            val updatedCurrencyValue = when(value){

                "C" -> "0.00"
                else -> if (currentCurrenyValue == "0.00") value else currentCurrenyValue + value

            }

            val numberFormat = DecimalFormat("#.00")

            when(state.selection){

                SelectionState.FROM->{
                    val fromValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                    val toValue = fromValue / fromCurrencyRate * toCurrenyRate
                    state = state.copy(

                        fromCurrencyValue = updatedCurrencyValue,
                        toCurrencyValue = numberFormat.format(toValue)
                    )
                }
                SelectionState.TO ->{

                    val toValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                    val fromValue = toValue / toCurrenyRate * fromCurrencyRate
                    state = state.copy(

                        toCurrencyValue = updatedCurrencyValue,
                        fromCurrencyValue = numberFormat.format(fromValue)
                    )

                }
            }
        }
}