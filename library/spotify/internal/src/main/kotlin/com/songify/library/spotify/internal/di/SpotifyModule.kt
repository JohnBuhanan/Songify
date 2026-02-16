package com.songify.library.spotify.internal.di

import androidx.paging.PagingConfig
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.paging.SpotifyPagingSource
import com.songify.library.spotify.internal.usecase.GetCategoriesImpl
import com.songify.library.spotify.internal.usecase.GetFeaturedPlaylistsImpl
import com.songify.library.spotify.internal.usecase.GetNewReleasesImpl
import com.songify.library.spotify.internal.usecase.GetPlaylistsForCategoryImpl
import com.songify.library.spotify.internal.usecase.GetTracksByAlbumIdImpl
import com.songify.library.spotify.internal.usecase.GetTracksByPlaylistIdImpl
import com.songify.library.spotify.usecase.GetCategories
import com.songify.library.spotify.usecase.GetFeaturedPlaylists
import com.songify.library.spotify.usecase.GetNewReleases
import com.songify.library.spotify.usecase.GetPlaylistsForCategory
import com.songify.library.spotify.usecase.GetTracksByAlbumId
import com.songify.library.spotify.usecase.GetTracksByPlaylistId
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import retrofit2.Retrofit
import retrofit2.create

@ContributesTo(AppScope::class)
interface SpotifyModule {
    @Binds
    fun bindsGetCategoriesImpl(impl: GetCategoriesImpl): GetCategories

    @Binds
    fun bindsGetFeaturedPlaylistsImpl(impl: GetFeaturedPlaylistsImpl): GetFeaturedPlaylists

    @Binds
    fun bindsGetNewReleasesImpl(impl: GetNewReleasesImpl): GetNewReleases

    @Binds
    fun bindsGetPlaylistsForCategoryImpl(impl: GetPlaylistsForCategoryImpl): GetPlaylistsForCategory

    @Binds
    fun bindsGetTracksByAlbumIdImpl(impl: GetTracksByAlbumIdImpl): GetTracksByAlbumId

    @Binds
    fun bindsGetTracksByPlaylistIdImpl(impl: GetTracksByPlaylistIdImpl): GetTracksByPlaylistId

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
