package org.raam.quizkmp.presentation.quiz

import org.raam.quizkmp.domain.model.Question

data class QuizUiState(
    val currentQuestionIndex: Int = 0,
    val questions: List<Question> = emptyList(),
    val selectedOption: Int? = null,
    val correctCount: Int = 0,
    val streak: Int = 0,
    val maxStreak: Int = 0,
    val isLoading: Boolean = true,
    val isCompleted: Boolean = false
)
