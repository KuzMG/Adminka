package com.example.adminka.data

import org.json.JSONArray
import org.json.JSONObject

data class Task(
    var id: Int,
    var task: String,
    var active: Boolean,
    var idEmployee: Int
) {
    companion object {
        fun fromResponse(body: String): MutableList<Task> {
            try {
                val jsonArray = JSONArray(body)
                val list = mutableListOf<Task>()
                for (i in 0 until jsonArray.length()) {
                    val data = jsonArray.getJSONObject(i)
                    list.add(
                        Task(
                            data.getInt("id"),
                            data.getString("task"),
                            data.getBoolean("active"),
                            data.getInt("id_employee")
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
