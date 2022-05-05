package com.htueko.thesilverscreen.data.remote.dto

import com.htueko.thesilverscreen.data.util.JsonReader

object NowPlayingResponse {
    fun getSuccessResponse(): String {
        return JsonReader.getJson("NowPlayingResponse.json")
    }
}

object PopularResponse {
    fun getSuccessResponse(): String {
        return JsonReader.getJson("PopularResponse.json")
    }
}

object TopRatedResponse {
    fun getSuccessResponse(): String {
        return JsonReader.getJson("TopRatedResponse.json")
    }
}

object UpComingResponse {
    fun getSuccessResponse(): String {
        return JsonReader.getJson("UpComingResponse.json")
    }
}

object MovieDetailResponse {
    fun getSuccessResponse(): String {
        return JsonReader.getJson("MovieDetailResponse.json")
    }
}
