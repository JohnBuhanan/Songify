package com.songify.feature.nowplaying.app.di

import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.nowplaying.NowPlayingScreen
import com.songify.library.spotify.model.SpotifyModel.Track
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

private val track = Track(
    id = "3n3Ppam7vgaVa1iaRUc9Lp",
    caption = "THE TORTURED POETS DEPARTMENT",
    imageUrlString = "https://i.scdn.co/image/ab67616d00001e025076e4160d018e378f488c33",
    artistsString = "Taylor Swift",
    trackUrlString = "https://i.scdn.co/image/ab67616d00001e025076e4160d018e378f488c33",
)

@ContributesTo(AppScope::class)
interface NowPlayingAppModule {
    companion object {
        @SingleIn(AppScope::class)
        @Provides
        fun providesNowPlayingStartScreen(): Screen = NowPlayingScreen(track)
    }
}
