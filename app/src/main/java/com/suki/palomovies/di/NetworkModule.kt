package com.suki.palomovies.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.suki.palomovies.patform.api.Constants.Api.CONNECT_TIMEOUT_IN_S
import com.suki.palomovies.patform.api.Constants.Api.READ_TIMEOUT_IN_S
import com.suki.palomovies.patform.api.Constants.Api.WRITE_TIMEOUT_IN_S
import com.suki.palomovies.patform.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context) = OkHttpClient()
        .newBuilder()
        .connectTimeout(CONNECT_TIMEOUT_IN_S, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_IN_S, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_IN_S, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, moshi: Moshi): MovieApi = Retrofit.Builder()
        .baseUrl(MovieApi.END_POINT)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create(MovieApi::class.java)

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}