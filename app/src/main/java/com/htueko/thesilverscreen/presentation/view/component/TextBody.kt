package com.htueko.thesilverscreen.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
@Suppress("FunctionNaming")
fun BodyTextComponent(
    text: String
) {
    Column(
        modifier = Modifier
            .padding(
                start = 4.dp,
                end = 4.dp,
                bottom = 2.dp,
                top = 2.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Normal
        )
    }
}
