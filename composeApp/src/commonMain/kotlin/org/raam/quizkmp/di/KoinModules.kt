package org.raam.quizkmp.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import org.raam.quizkmp.data.local.DatabaseDriverFactory
import org.raam.quizkmp.data.local.QuizDatabase
import org.raam.quizkmp.data.repository.QuizRepositoryImpl
import org.raam.quizkmp.domain.repository.QuizRepository
import org.raam.quizkmp.domain.usecase.GetQuestionsUseCase
import org.raam.quizkmp.presentation.quiz.QuizViewModel


val appModule = module {

    single { HttpClient { install(ContentNegotiation) { json() } } }

    // Data
    single { QuizDatabase(driver = get<DatabaseDriverFactory>().createDriver()) }

    single<QuizRepository> { QuizRepositoryImpl(get(),get()) }

    // UseCases
    factory { GetQuestionsUseCase(get()) }

    // ViewModels
    single { QuizViewModel(get()) }
}


