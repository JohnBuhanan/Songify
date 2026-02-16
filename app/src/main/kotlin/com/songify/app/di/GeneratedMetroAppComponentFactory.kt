package com.songify.app.di

import android.app.Application
import androidx.`annotation`.Keep
import com.songify.library.metro.AppGraph
import com.songify.library.metro.MetroAppComponentFactory
import dev.zacsweers.metro.createGraph

@Keep
class GeneratedMetroAppComponentFactory : MetroAppComponentFactory() {
    override fun createAppGraph(app: Application): AppGraph {
        val metroGraph = createGraph<MetroGraph>()
//        val appGraph = (metroGraph as AppGraph.Factory).createAppGraph(app)
        val appGraph = metroGraph.appGraph

        return appGraph
    }
}
