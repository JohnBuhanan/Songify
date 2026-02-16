package com.songify.library.search.internal.di

import com.songify.library.search.internal.usecase.GetGenresImpl
import com.songify.library.search.usecase.GetGenres
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo

@ContributesTo(AppScope::class)
interface LibrarySearchModule {
    @Binds
    fun bindsGetGenres(impl: GetGenresImpl): GetGenres
}
