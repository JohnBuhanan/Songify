// Copyright (C) 2022 Slack Technologies, LLC
// SPDX-License-Identifier: Apache-2.0
package com.songify.library.circuit.di

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn

@ContributesTo(AppScope::class)
interface CircuitModule {
    companion object {
        @SingleIn(AppScope::class)
        @Provides
        fun provideCircuit(
            presenterFactories: Set<Presenter.Factory>,
            uiFactories: Set<Ui.Factory>,
        ): Circuit {
            return Circuit.Builder()
                .apply {
                    for (factory in presenterFactories) {
                        addPresenterFactory(factory)
                    }
                    for (factory in uiFactories) {
                        addUiFactory(factory)
                    }
                }
                .build()
        }
    }
}
