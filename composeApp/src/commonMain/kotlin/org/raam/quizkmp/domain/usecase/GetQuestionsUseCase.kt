package org.raam.quizkmp.domain.usecase

import org.raam.quizkmp.domain.model.Question
import org.raam.quizkmp.domain.repository.QuizRepository

class GetQuestionsUseCase(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(): List<Question> {
        return repository.getQuiz()
    }
}

