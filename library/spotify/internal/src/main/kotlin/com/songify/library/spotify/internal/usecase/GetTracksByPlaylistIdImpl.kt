package com.songify.library.spotify.internal.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.songify.library.session.SongifySession
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.paging.PlaylistTracksPagingSource
import com.songify.library.spotify.model.SpotifyModel.Track
import com.songify.library.spotify.usecase.GetTracksByPlaylistId
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.Flow

@SingleIn(AppScope::class)
class GetTracksByPlaylistIdImpl @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val songifySession: SongifySession,
    private val spotifyService: SpotifyService,
) : GetTracksByPlaylistId {
    override suspend operator fun invoke(playlistId: String): Flow<PagingData<Track>> =
        Pager(pagingConfig) {
            PlaylistTracksPagingSource(
                playlistId = playlistId,
                songifySession = songifySession,
                spotifyService = spotifyService,
            )
        }.flow
}