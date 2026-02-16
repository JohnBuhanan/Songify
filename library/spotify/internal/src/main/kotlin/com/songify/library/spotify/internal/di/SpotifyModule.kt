package com.songify.library.spotify.internal.di

import androidx.paging.PagingConfig
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.paging.SpotifyPagingSource
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import retrofit2.Retrofit
import retrofit2.create

@ContributesTo(AppScope::class)
interface SpotifyModule {
    companion object {
        @Provides
        fun providesSpotifyService(retrofit: Retrofit): SpotifyService = retrofit.create()

        @Provides
        fun providesPagingConfig(): PagingConfig = PagingConfig(
            pageSize = SpotifyPagingSource.DEFAULT_PAGE_SIZE,
            initialLoadSize = SpotifyPagingSource.DEFAULT_PAGE_SIZE
        )
    }
}
