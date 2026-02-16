package com.songify.library.genre.internal.di

import com.songify.library.genre.internal.usecase.GetGenresImpl
import com.songify.library.genre.usecase.GetGenres
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo

@ContributesTo(AppScope::class)
interface LibrarySearchModule {
    @Binds
    fun bindsGetGenres(impl: GetGenresImpl): GetGenres
}
