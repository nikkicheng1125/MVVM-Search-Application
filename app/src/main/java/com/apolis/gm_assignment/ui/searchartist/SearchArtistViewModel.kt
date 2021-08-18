package com.apolis.gm_assignment.ui.searchartist

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apolis.gm_assignment.data.remote.IRepository
import com.apolis.gm_assignment.data.remote.response.SearchResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SearchArtistViewModel(val repository: IRepository): ViewModel() {

    val searchResponse = MutableLiveData<SearchResponse>()
    val processing = ObservableField<Boolean>(false)
    val error = MutableLiveData<String>()


    public fun search(searchTerm: String) {

        if(searchTerm.isEmpty()) {
            error.postValue("Please enter artist name to search")
            return;
        }

        viewModelScope.launch(IO) {
            try {
                processing.set(true)
                val response = repository.search(searchTerm)
                processing.set(false)
                if(response.isSuccessful) {
                    searchResponse.postValue(response.body())
                    if(searchResponse.value?.resultCount == 0) {
                        error.postValue("No data found for given search input.")
                    }
                } else {
                    error.postValue("Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                processing.set(false)
                e.printStackTrace()
                error.postValue("Error is: ${e.toString()}")
            }
        }
    }
}