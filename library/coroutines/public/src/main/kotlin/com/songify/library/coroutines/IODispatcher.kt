package com.songify.library.coroutines

import dev.zacsweers.metro.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class IODispatcher
