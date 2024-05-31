package com.example.adminka.data

import org.json.JSONArray
import org.json.JSONObject

data class CPU(
    var id: Int,
    var model: String,
    var cores: Int,
    var threads: Int,
    var frequency: String
){
    companion object{
        fun fromResponse(body: String): MutableList<CPU> {
            try {
                val jsonArray = JSONArray(body)
                val list = mutableListOf<CPU>()
                for(i in 0 until jsonArray.length()){
                    val data = jsonArray.getJSONObject(i)
                    list.add(CPU(
                        data.getInt("id"),
                        data.getString("model"),
                        data.getInt("cores"),
                        data.getInt("threads"),
                        data.getString("frequency")
                    ))
                }

                return list
            } catch (e: Exception) {
                return mutableListOf()
            }
        }
    }
}
