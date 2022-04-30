package com.htueko.thesilverscreen.presentation.view.home.topappbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Suppress("FunctionNaming")
fun AppBar(
    title: String
) {
    TopAppBar(modifier = Modifier.padding(12.dp)) {
        Text(text = title)
    }
}
