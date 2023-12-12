package uk.ac.tees.c2704207.currency.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.c2704207.currency.domain.model.CurrencyRate

@Composable
fun BottomSheetContent(
    onItemClicked: (String) -> Unit,
    currenciesList: List<CurrencyRate>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(currenciesList) { currency ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(currency.name) }
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            ) {
                Text(text = "${currency.code}: ${currency.name}")
            }
        }
    }
}