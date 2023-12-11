package uk.ac.tees.c2704207.currency.presentation.main_screen

sealed class MainScreenEvents {

    object FromCurrencySelect: MainScreenEvents()
    object ToCurrencySelect: MainScreenEvents()
    object SwapIconClicked: MainScreenEvents()
    data class BottomSheetItemClicked(val value: String): MainScreenEvents()
    data class NumberButtomClicked(val value: String): MainScreenEvents()





}