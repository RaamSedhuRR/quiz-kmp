package org.raam.quizkmp.presentation.quiz

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.raam.quizkmp.domain.usecase.GetQuestionsUseCase

class QuizViewModel(
    private val getQuestionsUseCase: GetQuestionsUseCase
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            val questions = getQuestionsUseCase()
            _uiState.value = _uiState.value.copy(
                questions = questions,
                isLoading = false
            )
        }
    }

    fun onOptionSelected(selectedIndex: Int) {
        val state = _uiState.value
        val currentQuestion = state.questions[state.currentQuestionIndex]
        val isCorrect = "$selectedIndex" == "${currentQuestion.correctOptionIndex}"

        val newStreak = if (isCorrect) state.streak + 1 else 0
        val newMax = maxOf(state.maxStreak, newStreak)

        _uiState.value = state.copy(
            selectedOption = selectedIndex,
            correctCount = if (isCorrect) state.correctCount + 1 else state.correctCount,
            streak = newStreak,
            maxStreak = newMax
        )

        // Advance to next question after delay (2s)
        viewModelScope.launch {
            kotlinx.coroutines.delay(2000)
            nextQuestion()
        }
        viewModelScope.launch {
            kotlinx.coroutines.delay(2000)
            prevQuestion()
        }
    }

    fun moveToNextQuestion() {
        nextQuestion()
    }

    fun moveToPrevQuestion() {
        prevQuestion()
    }

    private fun nextQuestion() {
        val state = _uiState.value
        if (state.currentQuestionIndex < state.questions.lastIndex) {
            _uiState.value = state.copy(
                currentQuestionIndex = state.currentQuestionIndex + 1,
                selectedOption = null
            )
        } else {
            _uiState.value = state.copy(isCompleted = true)
        }
    }

    private fun prevQuestion(){
        val state = _uiState.value
        if (state.currentQuestionIndex > 0) {
            _uiState.value = state.copy(
                currentQuestionIndex = state.currentQuestionIndex - 1
            )
        }
    }

    fun restartQuiz() {
        _uiState.value = QuizUiState(questions = _uiState.value.questions, isLoading = false)
    }
}
