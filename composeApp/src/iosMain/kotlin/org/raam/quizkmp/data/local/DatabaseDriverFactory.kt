package org.raam.quizkmp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.raam.quizkmp.db.QuizKmpDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(QuizKmpDatabase.Schema, "quiz.db")
}
