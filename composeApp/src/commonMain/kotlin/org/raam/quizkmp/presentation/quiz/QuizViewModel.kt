package org.raam.quizkmp.presentation.quiz

import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.raam.quizkmp.domain.usecase.GetQuestionsUseCase
import org.raam.quizkmp.presentation.utils.UiStateHandler

class QuizViewModel(
    private val getQuestionsUseCase: GetQuestionsUseCase
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val _uiState = MutableStateFlow<UiStateHandler<QuizUiState>>(UiStateHandler.Loading)
    val uiState: StateFlow<UiStateHandler<QuizUiState>> = _uiState

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            _uiState.value = UiStateHandler.Loading
            try {
                val questions = getQuestionsUseCase()
                val newState = QuizUiState(questions = questions, isLoading = false)
                _uiState.value = UiStateHandler.Success(newState)
            } catch (e: Exception) {
                _uiState.value = UiStateHandler.Error("${e.message}", e)
            }
        }
    }

    fun onOptionSelected(selectedIndex: Int) {
        val currentUi = _uiState.value
        if (currentUi !is UiStateHandler.Success) return

        val state = currentUi.data
        val questionIndex = state.currentQuestionIndex
        val currentQuestion = state.questions[questionIndex]
        val isCorrect = selectedIndex == currentQuestion.correctOptionIndex

        val newStreak = if (isCorrect) state.streak + 1 else 0
        val newMax = maxOf(state.maxStreak, newStreak)

        // Create updated selected options map
        val updatedSelections = state.selectedOptions.toMutableMap()
        updatedSelections[questionIndex] = selectedIndex

        val updatedState = state.copy(
            selectedOptions = updatedSelections,
            correctCount = if (isCorrect) state.correctCount + 1 else state.correctCount,
            streak = newStreak,
            maxStreak = newMax
        )

        _uiState.value = UiStateHandler.Success(updatedState)

        viewModelScope.launch {
            kotlinx.coroutines.delay(2000)
            nextQuestion()
        }
    }


    fun moveToNextQuestion() {
        nextQuestion()
    }

    fun moveToPrevQuestion() {
        prevQuestion()
    }

    private fun nextQuestion() {
        val currentUi = _uiState.value
        if (currentUi !is UiStateHandler.Success) return
        val state = currentUi.data

        val updated = if (state.currentQuestionIndex < state.questions.lastIndex) {
            state.copy(
                currentQuestionIndex = state.currentQuestionIndex + 1
            )
        } else {
            state.copy(isCompleted = true)
        }
        _uiState.value = UiStateHandler.Success(updated)
    }

    private fun prevQuestion() {
        val currentUi = _uiState.value
        if (currentUi !is UiStateHandler.Success) return
        val state = currentUi.data

        if (state.currentQuestionIndex > 0) {
            val updated = state.copy(
                currentQuestionIndex = state.currentQuestionIndex - 1
            )
            _uiState.value = UiStateHandler.Success(updated)
        }
    }

    fun restartQuiz() {
        val currentUi = _uiState.value
        if (currentUi !is UiStateHandler.Success) return

        val resetState = QuizUiState(
            questions = currentUi.data.questions,
            isLoading = false
        )
        _uiState.value = UiStateHandler.Success(resetState)
    }
}
