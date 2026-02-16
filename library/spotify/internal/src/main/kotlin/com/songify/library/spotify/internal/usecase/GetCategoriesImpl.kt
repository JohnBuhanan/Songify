package com.songify.library.spotify.internal.usecase

import com.songify.library.session.SongifySession
import com.songify.library.spotify.internal.SpotifyService
import com.songify.library.spotify.model.Category
import com.songify.library.spotify.usecase.GetCategories
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
class GetCategoriesImpl @Inject constructor(
    private val spotifyService: SpotifyService,
    private val songifySession: SongifySession,
) : GetCategories {
    override suspend operator fun invoke(): List<Category> =
        spotifyService.getBrowseCategories(songifySession.requireAccessToken())
            .categories
            .items
            .map { Category(it.id, it.name) }
}
