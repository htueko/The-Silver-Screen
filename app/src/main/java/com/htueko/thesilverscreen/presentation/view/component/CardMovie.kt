package com.htueko.thesilverscreen.presentation.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.presentation.view.home.bottomappbar.Screen

@ExperimentalCoilApi
@Composable
@Suppress("FunctionNaming")
fun MovieCardComponent(data: Movie, navController: NavController) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                val id = data.id
                navController.navigate(Screen.Detail.route + "/$id")
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                val painter = rememberImagePainter(
                    data = Movie().getBackDropImageUrl(data.backdropPath),
                )
                val painterState = painter.state
                if (painterState is ImagePainter.State.Loading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
                Image(
                    painter = painter,
                    contentDescription = data.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp))
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TitleTextComponent(text = data.title)
            }
        }
    }
}
