package com.songify.app.di

import com.songify.library.metro.MetroAppScope
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(scope = MetroAppScope::class)
interface MetroGraph
