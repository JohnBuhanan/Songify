package com.songify.feature.search.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.feature.search.SearchScreen
import com.songify.library.search.usecase.GetGenres
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class SearchPresenter(
    private val getGenres: GetGenres,
    @Assisted private val navigator: Navigator,
) : Presenter<SearchState> {
    @Composable
    override fun present(): SearchState {
        val state by produceState<SearchState>(SearchState.Loading) {
            value = SearchState.Success(
                genres = getGenres(),
                eventSink = {
                    when (it) {
                        SearchEvent.TappedBack -> navigator.pop()
                        SearchEvent.TappedCard -> {}
                    }
                }
            )
        }

        return state
    }

    @CircuitInject(SearchScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): SearchPresenter
    }
}
