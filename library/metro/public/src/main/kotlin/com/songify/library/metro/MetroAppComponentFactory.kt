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
import android.content.Intent
import androidx.core.app.AppComponentFactory
import dev.zacsweers.metro.Provider
import kotlin.reflect.KClass

/**
 * Abstract base AppComponentFactory that manages ActivityRetainedGraph and ActivityGraph lifecycle.
 *
 * - ActivityRetainedGraph: Created when activity is instantiated, survives configuration changes
 * - ActivityGraph: Created in onCreate, destroyed in onDestroy (does NOT survive config changes)
 *
 * Activities are created normally (no constructor injection), and member injection happens
 * in onCreate via the ActivityGraph.
 *
 * Subclasses must implement [createAppGraph] to provide the Metro-generated graph.
 */
abstract class MetroAppComponentFactory : AppComponentFactory() {
    lateinit var appGraph: AppGraph

    /**
     * Create the AppGraph for this application.
     * Implementations should call createGraph<MetroGraph>() and cast it appropriately.
     */
    protected abstract fun createAppGraph(app: Application): AppGraph

    override fun instantiateApplicationCompat(cl: ClassLoader, className: String): Application {
        val app = super.instantiateApplicationCompat(cl, className)

        appGraph = createAppGraph(app)

        // Inject members into the Application
        appGraph.applicationMembersInjector[app::class]?.injectMembers(app)

        activityProviders = appGraph.activityProviders

        return app
    }

    private inline fun <reified T : Any> getInstance(
        cl: ClassLoader,
        className: String,
        providers: Map<KClass<out T>, Provider<T>>,
    ): T? {
        val clazz = Class.forName(className, false, cl).asSubclass(T::class.java)
        val modelProvider = providers[clazz.kotlin] ?: return null
        return modelProvider()
    }

    override fun instantiateActivityCompat(
        cl: ClassLoader,
        className: String,
        intent: Intent?,
    ): Activity {
        return getInstance(cl, className, activityProviders)
            ?: super.instantiateActivityCompat(cl, className, intent)
    }

    // AppComponentFactory can be created multiple times
    companion object {
        private lateinit var activityProviders: Map<KClass<out Activity>, Provider<Activity>>
    }
}