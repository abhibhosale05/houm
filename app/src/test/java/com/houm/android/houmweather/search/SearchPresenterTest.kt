package com.houm.android.houmweather.search

import com.houm.android.houmweather.feature.search.SearchLocation
import com.houm.android.houmweather.feature.search.SearchLocationPresenter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SearchPresenterTest {

    private lateinit var view: SearchLocation.View
    private lateinit var presenter: SearchLocation.Presenter

    @Before
    fun setup() {
        view = Mockito.mock(SearchLocation.View::class.java)
        presenter = SearchLocationPresenter()
        presenter.attachView(view)
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

    @Test
    fun testIsValidInputSuccess() {
        val lat = 19.0760
        val lng = 72.8777
        presenter.isValidInput(lat, lng)
        Mockito.verify(view, Mockito.times(1)).onValidInput()
    }

    @Test
    fun testIsValidInputFailed() {
        val lat = -100.0
        val lng = 72.8777
        presenter.isValidInput(lat, lng)
        Mockito.verify(view, Mockito.times(1)).onInvalidInput()
    }

}