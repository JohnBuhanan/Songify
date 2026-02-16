package com.songify.feature.home.app.di

import androidx.paging.PagingData
import com.slack.circuit.runtime.screen.Screen
import com.songify.feature.home.HomeScreen
import com.songify.library.home.model.HomeFeed
import com.songify.library.home.model.HomeFeedCarousel
import com.songify.library.home.usecase.GetHomeFeed
import com.songify.library.spotify.model.SpotifyModel.Playlist
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.flowOf

@ContributesTo(AppScope::class)
interface HomeAppModule {
    companion object {
        @SingleIn(AppScope::class)
        @Provides
        fun providesHomeStartScreen(): Screen = HomeScreen

        @Provides
        fun providesGetHomeFeed(): GetHomeFeed = object : GetHomeFeed {
            override suspend fun invoke(): Result<HomeFeed> {
                return Result.success(
                    HomeFeed(
                        listOf(
                            HomeFeedCarousel(
                                "1", "Thing", flowOf(
                                    PagingData.from(
                                        listOf(
                                            Playlist(
                                                "1a",
                                                "caption",
                                                null,
                                                "name",
                                                "ownerName",
                                                "totalNumberOfTracks"
                                            ),
                                            Playlist(
                                                "1b",
                                                "caption",
                                                null,
                                                "name",
                                                "ownerName",
                                                "totalNumberOfTracks"
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            }
        }
    }
}
