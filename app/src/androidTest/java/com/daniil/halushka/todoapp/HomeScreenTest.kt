package com.daniil.halushka.todoapp

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.LargeTest
import com.daniil.halushka.todoapp.presentation.screens.home.HomeScreen
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.UUID

@HiltAndroidApp
class TodoTestApplication : Application()

@HiltAndroidTest
@LargeTest
class HomeScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Composable
    @Test
    fun AddNewTask() {
        // 1. Открыть приложение
        composeTestRule.setContent {
            TodoAppTheme {
                HomeScreen(navigationController = rememberNavController())
            }
        }

        // 2. Нажать на «+»
        composeTestRule.onNodeWithContentDescription(stringResource(R.string.add_new_task)).performClick()

        val todoText = UUID.randomUUID().toString()

        // 3. Ввести описание задачи
        composeTestRule.onNodeWithTag(stringResource(R.string.field_to_text_of_todo)).performTextInput(todoText)

        // 4. Нажать «Сохранить»
        composeTestRule.onNodeWithContentDescription(stringResource(R.string.save_todo)).performClick()

        composeTestRule.onNodeWithContentDescription(stringResource(R.string.add_new_task)).assertExists()

        composeTestRule.onNodeWithText(todoText).assertExists()

        // Ожидаемый результат: новая задача отображается на экране
        composeTestRule.onNodeWithText(todoText).assertIsDisplayed()
    }
}
