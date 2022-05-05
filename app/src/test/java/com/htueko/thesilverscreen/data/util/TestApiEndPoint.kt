package com.htueko.thesilverscreen.data.util

import com.htueko.thesilverscreen.data.remote.RelativeConstant

enum class TestApiEndPoint(val value: String) {
    UPCOMING_MOVIES(RelativeConstant.upcoming),
    TOP_RATED_MOVIES(RelativeConstant.topRated),
    POPULAR_MOVIES(RelativeConstant.popular),
    NOW_PLAYING_MOVIES(RelativeConstant.nowPlaying),
    DETAIL_MOVIES("/{movieId}"),
}
