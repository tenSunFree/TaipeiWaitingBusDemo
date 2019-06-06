package com.home.taipeiwaitingbusdemo.http

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/"

val retrofit: Retrofit by lazy {
	Retrofit.Builder()
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
		.baseUrl(BASE_URL)
		.build()
}