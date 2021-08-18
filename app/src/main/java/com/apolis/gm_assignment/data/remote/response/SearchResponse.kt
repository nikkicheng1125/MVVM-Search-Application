package com.apolis.gm_assignment.data.remote.response

import com.apolis.gm_assignment.data.Artist
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("resultCount")
    val resultCount: Int,

    @SerializedName("results")
    val results: ArrayList<Artist>
)
