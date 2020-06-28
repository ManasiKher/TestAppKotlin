package com.example.testAppKotlin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Mock
    private val view: CanadaDetailsViewModel? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun fetchValidDataShouldLoadIntoView() {
        val canadaDetailsViewModel : CanadaDetailsViewModel
        val dataRepository = DataRepository(Mockito.mock(NetWorkServices::class.java))
        canadaDetailsViewModel = CanadaDetailsViewModel(dataRepository)
        assertNotNull(canadaDetailsViewModel.getDetails())

        val inOrder = Mockito.inOrder(view)
        inOrder.verify<CanadaDetailsViewModel>(view, times(1)).getDetails()
        inOrder.verify<CanadaDetailsViewModel>(view, times(1)).getData()
        inOrder.verify<CanadaDetailsViewModel>(view, times(1)).getErrors()
    }
    @Test
    fun fetchErrorData() {
        val canadaDetailsViewModel : CanadaDetailsViewModel
        val dataRepository = DataRepository(Mockito.mock(NetWorkServices::class.java))
        canadaDetailsViewModel = CanadaDetailsViewModel(dataRepository)
        assertNotNull(canadaDetailsViewModel.getErrors())
    }
}
