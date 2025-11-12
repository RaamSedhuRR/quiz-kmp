package org.raam.quizkmp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.raam.quizkmp.di.androidModule
import org.raam.quizkmp.di.appModule

class QuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuizApp)
            modules(listOf(appModule, androidModule))
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizKmpApp()
        }
    }
}
