package com.example.adminka.data

import org.json.JSONArray
import org.json.JSONObject

data class Employee(
    var id: Int,
    var fullName: String,
    var jobTitle: String,
    var phoneNumber: String,
    var experience: Int,
    var email: String,
    var salary: Int,
    var idRoom: Int,
    var age: Int

){
    companion object{
        fun fromResponse(body: String): MutableList<Employee> {
            try { val jsonArray = JSONArray(body)
                val list = mutableListOf<Employee>()
                for(i in 0 until jsonArray.length()){
                    val data = jsonArray.getJSONObject(i)
                    list.add(Employee(
                        data.getInt("id"),
                        data.getString("full_name"),
                        data.getString("job_title"),
                        data.getString("phone_number"),
                        data.getInt("experience"),
                        data.getString("email"),
                        data.getInt("salary"),
                        data.getInt("id_room"),
                        data.getInt("age")
                    ))
                }

                return list
            } catch (e: Exception) {
                return mutableListOf()
            }
        }
    }
}
