package com.apolis.gm_assignment.data.remote

class Repository(val apiService: ApiService): IRepository {
    override suspend fun search(searchTerm: String) = apiService.search(searchTerm)

}