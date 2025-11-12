package org.raam.quizkmp.di

import org.koin.dsl.module
import org.raam.quizkmp.data.local.DatabaseDriverFactory

val iosModule = module {
    single { DatabaseDriverFactory() }
}
