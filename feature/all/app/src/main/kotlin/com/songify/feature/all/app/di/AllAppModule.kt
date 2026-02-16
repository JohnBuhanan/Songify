package com.songify.feature.all.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.home.HomeScreen
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@ContributesTo(AppScope::class)
interface AllAppModule {
    companion object {
        @SingleIn(AppScope::class)
        @Provides
        fun providesAllStartScreen(): Screen = HomeScreen
    }
}
