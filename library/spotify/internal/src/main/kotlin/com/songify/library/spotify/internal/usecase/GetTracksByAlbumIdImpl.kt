package com.songify.library.spotify.internal.usecase

import com.songify.library.session.SongifySession
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.internal.model.getTracks
import com.songify.library.spotify.model.SpotifyModel
import com.songify.library.spotify.usecase.GetTracksByAlbumId
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.SingleIn
import retrofit2.HttpException
import java.io.IOException

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
class GetTracksByAlbumIdImpl(
    private val songifySession: SongifySession,
    private val spotifyService: SpotifyService,
) : GetTracksByAlbumId {
    override suspend operator fun invoke(albumId: String): Result<List<SpotifyModel.Track>> =
        try {
            val tracks = spotifyService.getAlbumWithId(
                albumId = albumId,
                token = songifySession.requireAccessToken()
            ).getTracks()

            Result.success(tracks)
        } catch (httpException: HttpException) {
            Result.failure(httpException)
        } catch (ioException: IOException) {
            Result.failure(ioException)
        }
}
