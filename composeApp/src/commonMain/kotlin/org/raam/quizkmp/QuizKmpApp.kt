package org.raam.quizkmp


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.raam.quizkmp.presentation.quiz.QuizViewModel
import org.koin.compose.koinInject
import org.raam.quizkmp.presentation.quiz.screens.QuizScreen
import org.raam.quizkmp.presentation.quiz.screens.ResultScreen
import org.raam.quizkmp.presentation.quiz.screens.SplashScreen


@Composable
fun QuizKmpApp() {
    val viewModel: QuizViewModel = koinInject()
    val state = viewModel.uiState.collectAsState()

    MaterialTheme {
        when {
            state.value.isLoading -> SplashScreen()
            state.value.isCompleted -> ResultScreen(
                correct = state.value.correctCount,
                total = state.value.questions.size,
                maxStreak = state.value.maxStreak,
                onRestart = { viewModel.restartQuiz() }
            )
            else -> QuizScreen(
                state = state.value,
                onOptionSelected = { viewModel.onOptionSelected(it) },
                onSkip = { viewModel.skipQuestion() },
                onNext= { null }
            )
        }
    }
}
