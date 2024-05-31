package com.example.adminka.data

import org.json.JSONArray
import org.json.JSONObject

data class Server(
    var id: Int,
    var name: String,
    var price: Int,
    var id_cpu: String,
    var id_ram: String,
    var id_drive: String
){
    companion object{
        fun fromResponse(body: String): MutableList<Server> {
            try {
                val jsonArray = JSONArray(body)
                val list = mutableListOf<Server>()
                for(i in 0 until jsonArray.length()){
                    val data = jsonArray.getJSONObject(i)
                    list.add(Server(
                        data.getInt("id"),
                        data.getString("full_name"),
                        data.getInt("price"),
                        data.getString("id_cpu"),
                        data.getString("item_id_ram.xml"),
                        data.getString("id_price")))
                }

                return list
            } catch (e: Exception) {
                return mutableListOf()
            }
        }
    }
}
