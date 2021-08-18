package com.apolis.gm_assignment.data

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("artistName")
    val artistName: String,

    @SerializedName("trackName")
    val trackName: String,

    @SerializedName("trackPrice")
    val trackPrice: Float,

    @SerializedName("primaryGenreName")
    val genre: String,

    @SerializedName("releaseDate")
    val releaseDate: String

)
