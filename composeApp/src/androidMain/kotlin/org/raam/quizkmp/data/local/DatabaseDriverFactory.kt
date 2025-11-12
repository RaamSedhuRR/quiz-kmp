// androidMain
package org.raam.quizkmp.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.raam.quizkmp.db.QuizKmpDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(QuizKmpDatabase.Schema, context, "quiz.db")
}

