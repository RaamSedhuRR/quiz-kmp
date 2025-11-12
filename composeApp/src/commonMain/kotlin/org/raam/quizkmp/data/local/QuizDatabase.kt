package org.raam.quizkmp.data.local

import app.cash.sqldelight.db.SqlDriver
import com.raam.quizkmp.db.QuizKmpDatabase

class QuizDatabase(driver: SqlDriver) {
    private val database = QuizKmpDatabase(driver)
    val questionQueries = database.questionQueries
}
