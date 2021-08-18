package com.apolis.gm_assignment.ui.searchartist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolis.gm_assignment.R
import com.apolis.gm_assignment.data.remote.ApiClient
import com.apolis.gm_assignment.data.remote.Repository
import com.apolis.gm_assignment.databinding.ActivitySearchArtistBinding
import com.apolis.gm_assignment.ui.BaseActivity
import com.apolis.gm_assignment.ui.adapters.ArtistAdapter

class SearchArtistActivity : BaseActivity() {

    lateinit var viewModel: SearchArtistViewModel
    lateinit var binding: ActivitySearchArtistBinding
    lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_artist)
        setupViewModel()
        setUpObservers()
        setUpListeners()

        adapter = ArtistAdapter(emptyList())
        binding.rvArtists.layoutManager = LinearLayoutManager(baseContext)
        binding.rvArtists.adapter = adapter

        binding.viewModel = viewModel
    }

    private fun setUpListeners() {
        binding.btnSearch.setOnClickListener {
            val searchTerm = binding.etSearch.text.toString()
            viewModel.search(searchTerm)
        }
    }

    fun setupViewModel() {
        val factory = SearchArtistViewModelFactory(Repository(ApiClient.apiService))
        viewModel = ViewModelProvider(this, factory).get(SearchArtistViewModel::class.java)
    }

    fun setUpObservers() {
        viewModel.error.observe(this) {
            showMessage(it, R.string.btn_ok)
        }

        viewModel.searchResponse.observe(this) {
            adapter.setNewList(it.results)
        }
    }
}