package uk.ac.tees.c2704207.currency.presentation.main_screen

import uk.ac.tees.c2704207.currency.domain.model.CurrencyRate

data class MainScreenState(

    val fromCurrencyCode: String = "GBP",
    val toCurrencyCode: String = "USD",
    val fromCurrencyValue: String = "0.00",
    val toCurrencyValue: String = "0.00",
    val selection: SelectionState = SelectionState.FROM,
    val currencyRates: Map<String, CurrencyRate> = emptyMap(),
    val error: String? = null

)


enum class SelectionState{

    FROM,
    TO

}