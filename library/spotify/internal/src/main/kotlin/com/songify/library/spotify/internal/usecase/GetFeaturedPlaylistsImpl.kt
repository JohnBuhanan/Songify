package com.songify.library.spotify.internal.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.songify.library.spotify.internal.paging.FeaturedPlaylistsPagingSource
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.usecase.GetFeaturedPlaylists
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.Flow

@SingleIn(AppScope::class)
class GetFeaturedPlaylistsImpl @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val featuredPlaylistsPagingSource: FeaturedPlaylistsPagingSource
) : GetFeaturedPlaylists {
    override operator fun invoke(): Flow<PagingData<SpotifyModel>> =
        Pager(pagingConfig) { featuredPlaylistsPagingSource }.flow
}
