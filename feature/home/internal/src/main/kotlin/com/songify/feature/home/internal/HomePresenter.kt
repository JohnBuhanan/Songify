package com.songify.feature.home.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.feature.detail.DetailScreen
import com.songify.feature.home.HomeScreen
import com.songify.library.home.usecase.GetHomeFeed
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class HomePresenter(
    private val getHomeFeed: GetHomeFeed,
    @Assisted private val navigator: Navigator,
) : Presenter<HomeState> {
    @Composable
    override fun present(): HomeState {
        val state by produceState<HomeState>(HomeState.Loading) {
            getHomeFeed().fold({ homeFeed ->
                value = HomeState.Success(
                    homeFeed = homeFeed,
                    eventSink = {
                        when (it) {
                            is HomeEvent.TappedBack -> navigator.pop()
                            is HomeEvent.TappedCard -> navigator.goTo(DetailScreen(it.spotifyModel))
                        }
                    }
                )
            }, {
                value = HomeState.Error(it.message ?: "No error message")
            })
        }

        return state
    }

    @CircuitInject(HomeScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): HomePresenter
    }
}
