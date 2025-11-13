package org.raam.quizkmp

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject
import org.raam.quizkmp.presentation.quiz.QuizUiState
import org.raam.quizkmp.presentation.quiz.QuizViewModel
import org.raam.quizkmp.presentation.quiz.screens.ErrorScreen
import org.raam.quizkmp.presentation.quiz.screens.QuizScreen
import org.raam.quizkmp.presentation.quiz.screens.ResultScreen
import org.raam.quizkmp.presentation.quiz.screens.SplashScreen
import org.raam.quizkmp.presentation.utils.UiStateHandler

@Composable
fun QuizKmpApp() {
    val viewModel: QuizViewModel = koinInject()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    MaterialTheme {
        when (val state = uiState.value) {
            is UiStateHandler.Loading -> SplashScreen()

            is UiStateHandler.Error -> ErrorScreen(state) {
                viewModel.retry()
            }

            is UiStateHandler.Success -> {
                val quizState: QuizUiState = state.data
                when {
                    quizState.isCompleted -> ResultScreen(
                        correct = quizState.correctCount,
                        total = quizState.questions.size,
                        maxStreak = quizState.maxStreak,
                        onRestart = { viewModel.restartQuiz() }
                    )

                    else -> QuizScreen(
                        state = quizState,
                        onOptionSelected = { viewModel.onOptionSelected(it) },
                        onNext = { viewModel.moveToNextQuestion() },
                        onPrev = { viewModel.moveToPrevQuestion() }
                    )
                }
            }
        }
    }
}
