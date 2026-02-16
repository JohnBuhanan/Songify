package com.songify.library.search.usecase

import com.songify.library.search.Genre

interface GetGenres {
    operator fun invoke(): List<Genre>
}
