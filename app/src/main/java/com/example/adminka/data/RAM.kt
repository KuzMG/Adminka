package com.example.adminka.data

import org.json.JSONArray
import org.json.JSONObject

data class RAM(
    var id: Int,
    var type: String,
    var memory: Int,

){
    companion object{
        fun fromResponse(body: String): MutableList<RAM> {
            try {
                val jsonArray = JSONArray(body)
                val list = mutableListOf<RAM>()
                for(i in 0 until jsonArray.length()){
                    val data = jsonArray.getJSONObject(i)
                    list.add(RAM(
                        data.getInt("id"),
                        data.getString("type"),
                        data.getInt("memory")
                    ))
                }

                return list
            } catch (e: Exception) {
                return mutableListOf()
            }
        }
    }
}
