package org.raam.quizkmp.domain.repository

import org.raam.quizkmp.domain.model.Question

interface QuizRepository {
    suspend fun getQuiz(): List<Question>
}
