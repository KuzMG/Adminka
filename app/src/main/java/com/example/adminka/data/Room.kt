package com.example.adminka.data

import org.json.JSONArray
import org.json.JSONObject

data class Room(
    var id: Int,
    var address: String,
    var postcode: Int,
    var phoneNumber: String,
    var numberOfEmployee: Int,
    var type: String
){
    companion object{
        fun fromResponse(body: String): MutableList<Room> {
            try {
                val jsonArray = JSONArray(body)
                val list = mutableListOf<Room>()
                for(i in 0 until jsonArray.length()) {
                    val data = jsonArray.getJSONObject(i)
                    list.add(
                        Room(
                            data.getInt("id"),
                            data.getString("address"),
                            data.getInt("postcode"),
                            data.getString("phone_number"),
                            data.getInt("number_of_employee"),
                            data.getString("type")
                        )
                    )
                }
                return list
            } catch (e: Exception) {
                return mutableListOf()
            }
        }
    }
}
