package com.htueko.thesilverscreen.presentation.view.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.htueko.thesilverscreen.R.string
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun ErrorStatus(hasError: Boolean, errorMessage: String) {

    var visibility by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ErrorStatusBox(hasError, errorMessage)
    }

    LaunchedEffect(hasError) {
        visibility = if (!hasError) {
            true
        } else {
            delay(2000)
            false
        }
    }
}

@Composable
fun ErrorStatusBox(hasError: Boolean, errorMessage: String) {

    val backgroundColor by animateColorAsState(if (hasError) Color.Green else Color.Red)

    val message: String? = if (hasError) {
        errorMessage
    } else {
        null
    }
    val iconResource: ImageVector? = if (hasError) {
        Icons.Rounded.Error
    } else {
        null
    }

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            if (iconResource != null) {
                Icon(
                    imageVector = iconResource,
                    contentDescription = stringResource(id = string.text_connectivity_icon),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            if (message != null) {
                Text(message, color = Color.White, fontSize = 15.sp)
            }
        }
    }
}
