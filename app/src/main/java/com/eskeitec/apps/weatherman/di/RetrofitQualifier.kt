package com.eskeitec.apps.weatherman.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWeather

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitPlaces


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClientPlaces

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClientWeather

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StringPlaces

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StringWeather



