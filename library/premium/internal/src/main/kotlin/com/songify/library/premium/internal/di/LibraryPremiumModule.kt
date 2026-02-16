package com.songify.library.premium.internal.di

import com.songify.library.premium.internal.usecase.GetPremiumPlansImpl
import com.songify.library.premium.usecase.GetPremiumPlans
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo

@ContributesTo(AppScope::class)
interface LibraryPremiumModule {
    @Binds
    fun bindsGetPremiumPlans(impl: GetPremiumPlansImpl): GetPremiumPlans
}
