package com.apolis.gm_assignment.data.remote

import com.apolis.gm_assignment.data.remote.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun search(@Query("term") searchTerm: String): Response<SearchResponse>
}