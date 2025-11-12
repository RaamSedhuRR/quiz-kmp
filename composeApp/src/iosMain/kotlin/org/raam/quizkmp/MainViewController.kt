package org.raam.quizkmp

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import org.raam.quizkmp.di.appModule
import org.raam.quizkmp.di.iosModule


fun MainViewController() = ComposeUIViewController {
    startKoin { modules(listOf(appModule, iosModule)) }
    QuizKmpApp()

}
