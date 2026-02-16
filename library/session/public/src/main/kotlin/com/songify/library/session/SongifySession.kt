package com.songify.library.session

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
class SongifySession @Inject constructor() {
    var accessToken: String? = null

    fun requireAccessToken(): String {
        return "Bearer " + requireNotNull(accessToken)
    }
}
