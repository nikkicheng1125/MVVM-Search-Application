package com.apolis.gm_assignment.ui.searchartist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apolis.gm_assignment.data.remote.IRepository
import java.io.InvalidClassException

class SearchArtistViewModelFactory(val repository: IRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchArtistViewModel::class.java)) {
            return SearchArtistViewModel(repository) as T
        }
        throw InvalidClassException("Invalid class: ${modelClass.simpleName}")
    }
}