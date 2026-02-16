package com.songify.app.di

import android.app.Application
import androidx.`annotation`.Keep
import com.songify.library.metro.AppGraph
import com.songify.library.metro.MetroAppComponentFactory
import dev.zacsweers.metro.createGraph

@Keep
class GeneratedMetroAppComponentFactory : MetroAppComponentFactory() {
    override fun createAppGraph(app: Application): AppGraph =
        (createGraph<MetroGraph>() as AppGraph.Factory).create(app)
}
