/*
 * Copyright 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.songify.library.metro

import android.app.Activity
import android.app.Application
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.MembersInjector
import dev.zacsweers.metro.Multibinds
import dev.zacsweers.metro.Provider
import kotlin.reflect.KClass

@GraphExtension(scope = AppScope::class)
interface AppGraph {
    @Multibinds(allowEmpty = true)
    val applicationMembersInjector: Map<KClass<*>, MembersInjector<Application>>
    val activityProviders: Map<KClass<out Activity>, Provider<Activity>>


//    @ContributesTo(MetroAppScope::class)
//    @GraphExtension.Factory
//    fun interface Factory {
//        fun createAppGraph(@Provides application: Application): AppGraph
//    }

//    @Provides
//    fun provideContext(application: Application): Context = application
}