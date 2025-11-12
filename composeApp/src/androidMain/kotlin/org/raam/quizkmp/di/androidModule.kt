package org.raam.quizkmp.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.raam.quizkmp.data.local.DatabaseDriverFactory

val androidModule = module {
    single { DatabaseDriverFactory(androidContext()) }
}
