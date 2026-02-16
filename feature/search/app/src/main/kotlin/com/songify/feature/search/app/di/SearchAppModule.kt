package com.songify.feature.search.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.search.SearchScreen
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@ContributesTo(AppScope::class)
interface SearchAppModule {
    companion object {
        @SingleIn(AppScope::class)
        @Provides
        fun providesSearchStartScreen(): Screen = SearchScreen
    }
}
