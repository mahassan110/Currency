package uk.ac.tees.c2704207.currency.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ){
        Text(
            modifier = Modifier.fillMaxSize(),
            text = "Currency",
            fontFamily = FontFamily.Serif,
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,

        )

    }

}

fun Text(modifier: Modifier) {
    TODO("Not yet implemented")
}

fun Column(modifier: Modifier) {
    TODO("Not yet implemented")
}
