package com.songify.library.home.internal.di

import com.songify.library.home.internal.usecase.GetHomeFeedImpl
import com.songify.library.home.usecase.GetHomeFeed
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo

@ContributesTo(AppScope::class)
interface LibraryHomeModule {
    @Binds
    fun bindsGetHomeFeed(impl: GetHomeFeedImpl): GetHomeFeed
}
