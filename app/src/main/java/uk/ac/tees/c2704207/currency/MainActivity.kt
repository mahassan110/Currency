package uk.ac.tees.c2704207.currency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import uk.ac.tees.c2704207.currency.presentation.main_screen.MainScreen
import uk.ac.tees.c2704207.currency.presentation.theme.CurrencyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyTheme {
                MainScreen()
            }
        }
    }
}
