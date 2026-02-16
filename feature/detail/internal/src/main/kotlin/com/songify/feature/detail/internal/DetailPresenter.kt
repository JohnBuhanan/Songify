package com.songify.feature.detail.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.feature.detail.DetailScreen
import com.songify.feature.nowplaying.NowPlayingScreen
import com.songify.library.detail.usecase.GetTracks
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class DetailPresenter(
    private val getTracks: GetTracks,
    @Assisted private val detailScreen: DetailScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<DetailState> {

    @Composable
    override fun present(): DetailState {
        val state by produceState<DetailState>(DetailState.Loading) {
            value = DetailState.Success(
                spotifyModel = detailScreen.spotifyModel,
                tracks = getTracks(detailScreen.spotifyModel),
                eventSink = {
                    when (it) {
                        is DetailEvent.TappedBack -> navigator.pop()
                        is DetailEvent.TappedTrack -> navigator.goTo(NowPlayingScreen(it.track))
                    }
                }
            )
        }

        return state
    }

    @CircuitInject(DetailScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(detailScreen: DetailScreen, navigator: Navigator): DetailPresenter
    }
}
