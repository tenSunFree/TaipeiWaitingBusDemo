package com.home.taipeiwaitingbusdemo.data

import com.google.gson.annotations.SerializedName

/**
 * 餐飲觀光資訊資料庫
 */
data class CateringTourismInformationDatabaseData(
    @SerializedName("XML_Head")
    val xMLHead: XMLHead
) {
    data class XMLHead(
        @SerializedName("Listname")
        val listname: String,
        @SerializedName("Language")
        val language: String,
        @SerializedName("Orgname")
        val orgname: String,
        @SerializedName("Updatetime")
        val updatetime: String,
        @SerializedName("Infos")
        val infos: Infos
    ) {
        data class Infos(
            @SerializedName("Info")
            val info: List<Info>
        ) {
            data class Info(
                @SerializedName("Id")
                val id: String,
                @SerializedName("Name")
                val name: String,
                @SerializedName("Description")
                val description: String,
                @SerializedName("Participation")
                val participation: String,
                @SerializedName("Location")
                val location: String,
                @SerializedName("Add")
                val add: String,
                @SerializedName("Tel")
                val tel: String,
                @SerializedName("Org")
                val org: String,
                @SerializedName("Start")
                val start: String,
                @SerializedName("End")
                val end: String,
                @SerializedName("Cycle")
                val cycle: String,
                @SerializedName("Noncycle")
                val noncycle: String,
                @SerializedName("Website")
                val website: String,
                @SerializedName("Picture1")
                val picture1: String,
                @SerializedName("Picdescribe1")
                val picdescribe1: String,
                @SerializedName("Picture2")
                val picture2: String,
                @SerializedName("Picdescribe2")
                val picdescribe2: String,
                @SerializedName("Picture3")
                val picture3: String,
                @SerializedName("Picdescribe3")
                val picdescribe3: String,
                @SerializedName("Px")
                val px: Double,
                @SerializedName("Py")
                val py: Double,
                @SerializedName("Class1")
                val class1: String,
                @SerializedName("Class2")
                val class2: String,
                @SerializedName("Map")
                val map: String,
                @SerializedName("Travellinginfo")
                val travellinginfo: String,
                @SerializedName("Parkinginfo")
                val parkinginfo: String,
                @SerializedName("Charge")
                val charge: String,
                @SerializedName("Remarks")
                val remarks: String
            )
        }
    }
}