package com.songify.library.coroutines.di

import com.songify.library.coroutines.IODispatcher
import com.songify.library.coroutines.MainDispatcher
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@ContributesTo(AppScope::class)
interface CoroutinesModule {
    companion object {
        @SingleIn(AppScope::class)
        @[Provides IODispatcher]
        fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

        @SingleIn(AppScope::class)
        @[Provides MainDispatcher]
        fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    }
}
