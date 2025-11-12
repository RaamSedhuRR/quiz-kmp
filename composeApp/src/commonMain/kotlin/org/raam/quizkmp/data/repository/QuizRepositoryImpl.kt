package org.raam.quizkmp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.raam.quizkmp.domain.model.Question
import org.raam.quizkmp.domain.repository.QuizRepository
import kotlinx.coroutines.withContext
import org.raam.quizkmp.data.local.QuizDatabase
import kotlinx.serialization.encodeToString


class QuizRepositoryImpl(
    private val client: HttpClient,
    db: QuizDatabase
) : QuizRepository {

    private val questionQueries = db.questionQueries

    override suspend fun getQuiz(): List<Question> {
        return withContext(Dispatchers.IO) {
            val cached = questionQueries.selectAll().executeAsList().map {
                Question(
                    id = it.id.toInt(),
                    question = it.question,
                    options = Json.decodeFromString<List<String>>(it.options),
                    correctOptionIndex = it.correctOptionIndex.toInt()
                )
            }

            if (cached.isNotEmpty()) return@withContext cached

            // TODO: we can move this url to constants
            val response: HttpResponse = client.get("https://gist.githubusercontent.com/dr-samrat/53846277a8fcb034e482906ccc0d12b2/raw")
            println("HTTP Status: ${response.status}")

            val jsonString: String = response.bodyAsText()
            println("Response Body: $jsonString")

            val quiz: List<Question> = Json.decodeFromString(ListSerializer(Question.serializer()), jsonString)

            // Save to cache
            questionQueries.transaction {
                quiz.forEach { q ->
                    questionQueries.insertQuestion(
                        id = q.id.toLong(),
                        question = q.question,
                        options = Json.encodeToString(q.options),
                        correctOptionIndex = q.correctOptionIndex.toLong()
                    )
                }
            }
            quiz
        }
    }
}

