package com.howshea.home.repository

import com.home.taipeiwaitingbusdemo.data.CateringTourismInformationDatabaseData
import com.home.taipeiwaitingbusdemo.http.retrofit
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {

    /** 餐飲觀光資訊資料庫 */
    @GET("activity_C_f.json")
    fun getCateringTourismInformationDatabaseData()
            : Observable<Response<CateringTourismInformationDatabaseData>>
}

object MainService : MainApi by retrofit.create(MainApi::class.java)