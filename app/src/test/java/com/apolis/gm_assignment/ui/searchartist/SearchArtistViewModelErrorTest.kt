package com.apolis.gm_assignment.ui.searchartist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.apolis.gm_assignment.data.remote.Repository
import com.nhaarman.mockitokotlin2.doThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchArtistViewModelErrorTest {

    @Mock
    lateinit var repository: Repository

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Test
    fun `search artist with empty input returns error`() {
        runBlockingTest {
            val viewModel = SearchArtistViewModel(repository)
            viewModel.error.observeForever(errorObserver)
            viewModel.search("")
            Mockito.verify(errorObserver).onChanged("Please enter artist name to search")
            viewModel.error.removeObserver(errorObserver)
        }
    }

    @Test(expected = java.lang.RuntimeException::class)
    fun `search artist test runtime exception`() {
        runBlockingTest {

            doThrow(RuntimeException("Failed to connect"))
                .`when`(repository.search("Smith"))

            val viewModel = SearchArtistViewModel(repository)
            viewModel.error.observeForever(errorObserver)
            viewModel.search("Smith")
            Mockito.verify(errorObserver).onChanged("Error is : Failed to connect")
            viewModel.error.removeObserver(errorObserver)
        }
    }
}