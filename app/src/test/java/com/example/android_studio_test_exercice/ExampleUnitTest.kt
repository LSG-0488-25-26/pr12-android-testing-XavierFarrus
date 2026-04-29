package com.example.android_studio_test_exercice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.state.ToggleableState
import com.example.android_studio_test_exercice.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun initialState_isCorrect() {
        assertTrue(viewModel.estatSwitch.value == true)
        assertFalse(viewModel.esVegetaria.value == true)
        assertFalse(viewModel.esVega.value == true)
        assertTrue(viewModel.esCarnivor.value == true)
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
        assertEquals("Messi", viewModel.selectedOption.value)
        assertEquals(0f, viewModel.sliderValue.value)
        assertFalse(viewModel.expanded.value == true)
        assertEquals("Opció A", viewModel.selectedItem.value)
        assertEquals("", viewModel.searchText.value)
        assertFalse(viewModel.showSnackbar.value == true)
        assertFalse(viewModel.toggleState.value == true)
    }

    @Test
    fun toggleEstatSwitch_changesState() {
        viewModel.toggleEstatSwitch()
        assertFalse(viewModel.estatSwitch.value == true)
        viewModel.toggleEstatSwitch()
        assertTrue(viewModel.estatSwitch.value == true)
    }

    @Test
    fun toggleEsVegetaria_changesState() {
        viewModel.toggleEsVegetaria()
        assertTrue(viewModel.esVegetaria.value == true)
    }

    @Test
    fun toggleEsVega_changesState() {
        viewModel.toggleEsVega()
        assertTrue(viewModel.esVega.value == true)
    }

    @Test
    fun toggleEsCarnivor_changesState() {
        viewModel.toggleEsCarnivor()
        assertFalse(viewModel.esCarnivor.value == true)
    }

    @Test
    fun toggleTriStateStatus_cyclesThroughStates() {
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Indeterminate, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.On, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
    }

    @Test
    fun setSelectedOption_updatesValue() {
        viewModel.setSelectedOption("Lamine Yamal")
        assertEquals("Lamine Yamal", viewModel.selectedOption.value)
    }

    @Test
    fun setSliderValue_updatesValue() {
        viewModel.setSliderValue(74f)
        assertEquals(74f, viewModel.sliderValue.value)
    }

    @Test
    fun setExpanded_updatesValue() {
        viewModel.setExpanded(true)
        assertTrue(viewModel.expanded.value == true)
        viewModel.setExpanded(false)
        assertFalse(viewModel.expanded.value == true)
    }

    @Test
    fun setSelectedItem_updatesValue() {
        viewModel.setSelectedItem("Opció B")
        assertEquals("Opció B", viewModel.selectedItem.value)
    }

    @Test
    fun setSearchText_updatesValue() {
        viewModel.setSearchText("test")
        assertEquals("test", viewModel.searchText.value)
    }

    @Test
    fun performSearch_setsSnackbarOnlyForNonBlankText() {
        viewModel.setSearchText(" ")
        viewModel.performSearch()
        assertFalse(viewModel.showSnackbar.value == true)

        viewModel.setSearchText("android")
        viewModel.performSearch()
        assertTrue(viewModel.showSnackbar.value == true)
    }

    @Test
    fun toggle_changesToggleState() {
        viewModel.toggle()
        assertTrue(viewModel.toggleState.value == true)
        viewModel.toggle()
        assertFalse(viewModel.toggleState.value == true)
    }
}