package com.example.android_studio_test_exercice

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.SemanticsMatcher
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun hasTextContaining(text: String): SemanticsMatcher {
        return SemanticsMatcher("Text contains '$text'") { node ->
            val texts = node.config.getOrElse(SemanticsProperties.Text) { emptyList() }
            texts.any { it.text.contains(text) }
        }
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.android_studio_test_exercice", appContext.packageName)
    }

    @Test
    fun switchAndCheckboxes_workAsExpected() {
        composeTestRule.onNodeWithTag("wifiSwitch_id").assertIsDisplayed().assertIsOn()
        composeTestRule.onNodeWithTag("wifiSwitch_id").performClick()
        composeTestRule.onNodeWithTag("wifiSwitch_id").assertIsOff()

        composeTestRule.onNodeWithTag("carnivorCheckbox_id").assertIsDisplayed().assertIsOn().assertIsNotEnabled()

        composeTestRule.onNodeWithTag("vegetariaCheckbox_id").assertIsDisplayed().assertIsOff().performClick().assertIsOn()
        composeTestRule.onNodeWithTag("vegaCheckbox_id").assertIsDisplayed().assertIsOff().performClick().assertIsOn()
    }

    @Test
    fun triStateCheckbox_cyclesStates() {
        composeTestRule.onNodeWithTag("triStateCheckbox_id").assertIsDisplayed().assertIsOff()
        composeTestRule.onNodeWithTag("triStateCheckbox_id").performClick()
        composeTestRule.onNodeWithTag("triStateCheckbox_id")
            .assert(
                SemanticsMatcher.expectValue(
                    SemanticsProperties.ToggleableState,
                    ToggleableState.Indeterminate
                )
            )
        composeTestRule.onNodeWithTag("triStateCheckbox_id").performClick()
        composeTestRule.onNodeWithTag("triStateCheckbox_id").assertIsOn()
        composeTestRule.onNodeWithTag("triStateCheckbox_id").performClick()
        composeTestRule.onNodeWithTag("triStateCheckbox_id").assertIsOff()
    }

    @Test
    fun radioButtons_updateSelection() {
        composeTestRule.onNodeWithTag("radio_Vinicius").assertIsDisplayed().assertIsNotEnabled()
        composeTestRule.onNodeWithTag("radio_Lamine Yamal").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("radio_Lamine Yamal").assertIsSelected()
        composeTestRule.onNodeWithTag("radio_Raphina").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("radio_Raphina").assertIsSelected()
    }

    @Test
    fun slider_changesVolumeText() {
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("volumeText_id")
            .assert(hasTextContaining("0%"))

        composeTestRule.onNodeWithTag("volumeSlider_id")
            .assertIsDisplayed()
            .performSemanticsAction(SemanticsActions.SetProgress) { setProgress ->
                setProgress(80f)
            }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("volumeText_id")
            .assert(hasTextContaining("80%"))
    }

    @Test
    fun dropdown_selectsOption() {
        composeTestRule.onNodeWithTag("dropdownText_id").assertIsDisplayed().assertHasClickAction().performClick()
        composeTestRule.onNodeWithTag("dropdownItem_Opció B").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("dropdownText_id").assertTextContains("Opció B")
    }

    @Test
    fun searchFieldAndButton_showResultMessage() {
        composeTestRule.onNodeWithTag("searchTextField_id").assertIsDisplayed().performTextInput("android")
        composeTestRule.onNodeWithTag("searchButton_id").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("searchResultText_id").assertIsDisplayed()
    }

    @Test
    fun toggleButton_changesText() {
        composeTestRule.onNodeWithTag("toggleButton_id").assertIsDisplayed().assertTextContains("Desactivat")
        composeTestRule.onNodeWithTag("toggleButton_id").performClick()
        composeTestRule.onNodeWithTag("toggleButton_id").assertTextContains("Activat")
    }
}