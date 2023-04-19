package com.aeax.testingdev

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Test {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var counter : MutableState<Int>

    @Before
    fun init() {
        counter = mutableStateOf(0)
    }

    @Test
    fun validate_text_exists() {
        val nm = "Pedro"
        composeTestRule.setContent {
            Greeting(name = nm)
        }

        composeTestRule.onNodeWithText(nm).assertExists()
    }

    @Test
    fun validate_text_does_not_exists() {
        val text = "Pedro"
        composeTestRule.setContent {
            Greeting(name = "")
        }

        composeTestRule.onNodeWithText(text).assertDoesNotExist()
    }

    @Test
    fun on_click_validate_negative_numbers_in_message() {
        val text = "Conteo"
        val textResult = "Conteo = -2"

        composeTestRule.setContent {
            TextResult(text = "$text = ${counter.value}")
            OperationButtons(onClick = { counter.value += it })
        }

        composeTestRule.onNodeWithTag("btnMinus").performClick()
        composeTestRule.onNodeWithTag("btnMinus").performClick()

        composeTestRule.onNodeWithText(textResult).assertExists()
    }

    @Test
    fun on_click_validate_positive_numbers_in_message() {
        val textResult = "Conteo = 2"
        val text = "Conteo"

        composeTestRule.setContent {
            TextResult(text = "$text = ${counter.value}")
            OperationButtons(onClick = { counter.value += it })
        }

        composeTestRule.onNodeWithTag("btnPlus").performClick()
        composeTestRule.onNodeWithTag("btnPlus").performClick()

        composeTestRule.onNodeWithText(textResult).assertExists()
    }
}