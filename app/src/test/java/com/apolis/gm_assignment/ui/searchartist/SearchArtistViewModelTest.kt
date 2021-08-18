package com.apolis.gm_assignment.ui.searchartist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.apolis.gm_assignment.FileReader
import com.apolis.gm_assignment.data.remote.Repository
import com.apolis.gm_assignment.data.remote.response.SearchResponse
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchArtistViewModelTest {

    @Mock
    lateinit var repository: Repository

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchArtistObserver: Observer<SearchResponse>

    @Mock
    private lateinit var errorObserver: Observer<String>


    @Test
    fun `search artist returns success test`() {
        runBlockingTest {
            val res = Response.success(
                Gson().fromJson(
                    FileReader.readStringFromFile("search_artist_success_response.json"),
                    SearchResponse::class.java
                )
            )
            Mockito.doReturn(res).`when`(repository).search("Smith")

            val viewModel = SearchArtistViewModel(repository)
            viewModel.searchResponse.observeForever(searchArtistObserver)

            viewModel.search("Smith")

            Mockito.verify(repository).search("Smith")


            Mockito.verify(searchArtistObserver).onChanged(
                Gson().fromJson(
                    FileReader.readStringFromFile("search_artist_success_response.json"),
                    SearchResponse::class.java
                )
            )
            assertEquals(viewModel.searchResponse.value?.resultCount, 50)
            viewModel.searchResponse.removeObserver(searchArtistObserver)
        }
    }

    @Test
    fun `search artist returns empty result set test`() {
        runBlockingTest {
            val res = Response.success(
                Gson().fromJson(
                    FileReader.readStringFromFile("search_artist_empty_response.json"),
                    SearchResponse::class.java
                )
            )
            Mockito.doReturn(res).`when`(repository).search("d6867868677")

            val viewModel = SearchArtistViewModel(repository)

            viewModel.error.observeForever(errorObserver)

            viewModel.search("d6867868677")

            Mockito.verify(repository).search("d6867868677")

            Mockito.verify(errorObserver).onChanged("No data found for given search input.")

            assertEquals(viewModel.searchResponse.value?.resultCount, 0)
            viewModel.error.removeObserver(errorObserver)
        }
    }


}