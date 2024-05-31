package com.example.adminka.data

import org.json.JSONArray
import org.json.JSONObject

data class Check(
    var id: Int,
    var idClient: String,
    var price: String,
    var date: String,
    var idServer: String

){
    companion object{
        fun fromResponse(body: String): MutableList<Check> {
            try {
                val jsonArray = JSONArray(body)
                val list = mutableListOf<Check>()
                for(i in 0 until jsonArray.length()){
                    val data = jsonArray.getJSONObject(i)
                    list.add(Check(
                        data.getInt("id"),
                        data.getString("id_client"),
                        data.getString("price"),
                        data.getString("date"),
                        data.getString("id_server")
                    ))
                }

                return list
            } catch (e: Exception) {
                return mutableListOf()
            }
        }
    }
}
