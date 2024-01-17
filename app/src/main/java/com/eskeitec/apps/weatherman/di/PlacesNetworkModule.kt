package com.eskeitec.apps.weatherman.di

import com.eskeitec.apps.weatherman.BuildConfig
import com.eskeitec.apps.weatherman.data.datasource.network.PlacesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlacesNetworkModule {

    @Provides
    @Singleton
    @StringPlaces
    fun providesPlacesBaseUrl(): String {
        return "https://maps.googleapis.com/"
    }

    @Singleton
    @Provides
    @OkhttpClientPlaces
    fun providesPlacesOkhttpClient(): OkHttpClient {
        val logInterceptor =
            HttpLoggingInterceptor { message -> Timber.d(message = message) }.setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BODY,
            )

        return OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .callTimeout(45, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @RetrofitPlaces
    fun providesPlacesRetrofit(
        @StringPlaces placesBaseUrl: String,
        @OkhttpClientPlaces okHttp: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(placesBaseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesPlacesApiService(@RetrofitPlaces retrofit: Retrofit): PlacesApi {
        return retrofit.create(PlacesApi::class.java)
    }
}
