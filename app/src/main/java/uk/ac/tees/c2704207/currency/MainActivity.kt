package uk.ac.tees.c2704207.currency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.c2704207.currency.presentation.main_screen.MainScreen
import uk.ac.tees.c2704207.currency.presentation.main_screen.MainScreenViewModel
import uk.ac.tees.c2704207.currency.presentation.theme.CurrencyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyTheme {
                val viewModel: MainScreenViewModel = hiltViewModel()
                Surface {

                MainScreen(
                    state = viewModel.state,
                    onEvents = viewModel::onEvent
                    )
                }
            }
        }
    }
}
