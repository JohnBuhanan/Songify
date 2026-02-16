package com.songify.library.detail.internal.di

import com.songify.library.detail.internal.usecase.GetTracksImpl
import com.songify.library.detail.usecase.GetTracks
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo

@ContributesTo(AppScope::class)
interface LibraryDetailModule {
    @Binds
    fun bindsGetTracks(impl: GetTracksImpl): GetTracks
}
