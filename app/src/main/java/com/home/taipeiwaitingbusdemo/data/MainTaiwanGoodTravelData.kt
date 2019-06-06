package com.home.taipeiwaitingbusdemo.data

import com.google.gson.annotations.SerializedName

data class MainTaiwanGoodTravelData(
    @SerializedName("northDataList")
    val northDataList: List<NorthData>, // 北部
    @SerializedName("centralDataList")
    val centralDataList: List<NorthData>, // 中部
    @SerializedName("southDataList")
    val southDataList: List<NorthData>, // 南部
    @SerializedName("eastDataList")
    val eastDataList: List<NorthData>, // 東部
    @SerializedName("outlyingIslandDataList")
    val outlyingIslandDataList: List<NorthData> // 離島
) {
    data class NorthData(
        @SerializedName("eventName")
        val eventName: String, // 活動名稱, 比如說 一日農夫之端午立蛋、芋頭芒果
        @SerializedName("introduction")
        val introduction: String, // 簡介, 比如說 在端午連假的開始，享受一場最洗滌身心靈的旅程，築夢新故鄉特別為大家安排了一場芋頭與芒果交會的旅程...
        @SerializedName("countyName")
        val countyName: String // 縣市名稱, 比如說 高雄
    )
}