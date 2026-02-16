package com.songify.library.retrofit.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val API_URL = "https://api.spotify.com/"

@ContributesTo(AppScope::class)
interface RetrofitModule {
    companion object {
        @SingleIn(AppScope::class)
        @Provides
        fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
            return OkHttpClient
                .Builder()
                .build()
        }

        @SingleIn(AppScope::class)
        @Provides
        fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        @SingleIn(AppScope::class)
        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            moshi: Moshi,
        ): Retrofit {
            return Retrofit
                .Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(API_URL)
                .build()
        }
    }
}
