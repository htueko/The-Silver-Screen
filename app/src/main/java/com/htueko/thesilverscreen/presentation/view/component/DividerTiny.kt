package com.htueko.thesilverscreen.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Suppress("FunctionNaming")
fun DividerTiny() {
    HorizontalSpacerTiny()
    Divider(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp))
    HorizontalSpacerTiny()
}
