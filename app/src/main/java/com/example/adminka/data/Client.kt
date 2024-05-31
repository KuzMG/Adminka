package com.example.adminka.data

import org.json.JSONArray
import org.json.JSONObject

data class Client(
    var id: Int,
    var fullName: String,
    var email: String,
    var phone: String
){
    companion object{
        fun fromResponse(body: String): MutableList<Client> {
            try {
                val jsonArray = JSONArray(body)
                val list = mutableListOf<Client>()
                for(i in 0 until jsonArray.length()){
                    val data = jsonArray.getJSONObject(i)
                    list.add(Client(
                        data.getInt("id"),
                        data.getString("full_name"),
                        data.getString("email"),
                        data.getString("phone")
                    ))
                }

                return list
            } catch (e: Exception) {
                return mutableListOf()
            }
        }
    }
}
