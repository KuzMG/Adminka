package com.example.adminka.network

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.adminka.data.CPU
import com.example.adminka.data.Check
import com.example.adminka.data.Client
import com.example.adminka.data.Drive
import com.example.adminka.data.Employee
import com.example.adminka.data.RAM
import com.example.adminka.data.Room
import com.example.adminka.data.Server
import com.example.adminka.data.Task
import com.squareup.okhttp.Call
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import org.json.JSONObject

const val KEY_PASSWORD = "password"
const val KEY_LOGIN = "login"
const val SETTINGS = "settings"
private const val URL = "http://192.168.0.104:8080"

class Manager(context: Context) {
    private val client = OkHttpClient()
    private val setting = context.getSharedPreferences(SETTINGS, AppCompatActivity.MODE_PRIVATE)
    private val editor = setting.edit()


    fun signIn(login: String, password: String): Call {
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/authentication")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }

    fun addRole(login_new: String,password_new: String, type: String,id_employee: Int): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("login_new", login_new)
            .accumulate("password_new", password_new)
            .accumulate("type", type)
            .accumulate("id_employee", id_employee)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/add_role")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }

    fun getTable(table: String): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/get_$table")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }

    fun <T> updateTable(table: String, id: String, key: String, value: T): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("id", id)
            .accumulate(key, value)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/$table")
            .put(requestBody)
            .build()
        return client.newCall(request)
    }

    fun delTable(table: String, id: String): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/$table/$id")
            .delete(requestBody)
            .build()
        return client.newCall(request)
    }

    fun insertTask(task: Task): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("task", task.task)
            .accumulate("active", false)
            .accumulate("id_employee", task.idEmployee)


        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/task")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }

    fun insertRoom(room: Room): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("address", room.address)
            .accumulate("postcode", room.postcode)
            .accumulate("phone_number", room.phoneNumber)
            .accumulate("number_of_employee", 0)
            .accumulate("type", room.type)


        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/room")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }

    fun insertEmployee(
        employee: Employee
    ): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("full_name", employee.fullName)
            .accumulate("job_title", employee.jobTitle)
            .accumulate("phone_number", employee.phoneNumber)
            .accumulate("experience", employee.experience)
            .accumulate("email", employee.email)
            .accumulate("salary", employee.salary)
            .accumulate("id_room", employee.idRoom)
            .accumulate("age", employee.age)


        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/employee")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }

    fun insertServer(
        server: Server
    ): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("name", server.name)
            .accumulate("price", server.price)
            .accumulate("id_cpu", server.id_cpu)
            .accumulate("id_ram", server.id_ram)
            .accumulate("id_drive", server.id_drive)


        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/employee")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }

    fun insertCPU(
        cpu: CPU
    ): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("model", cpu.model)
            .accumulate("cores", cpu.cores)
            .accumulate("threads", cpu.threads)
            .accumulate("frequency", cpu.frequency)


        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/employee")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }
    fun insertRam(
        ram: RAM
    ): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("type", ram.type)
            .accumulate("memory", ram.memory)

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/employee")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }
    fun insertDrive(
        drive: Drive
    ): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("type", drive.type)
            .accumulate("memory", drive.memory)


        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/employee")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }
    fun insertClient(
        client_: Client
    ): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("full_name", client_.fullName)
            .accumulate("email", client_.email)
            .accumulate("phone", client_.phone)


        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/employee")
            .post(requestBody)
            .build()

        return client.newCall(request)
    }
    fun insertCheck(
        check: Check
    ): Call {
        val login = setting.getString(KEY_LOGIN, "")
        val password = setting.getString(KEY_PASSWORD, "")
        val jsonData = JSONObject()
        jsonData
            .accumulate("login", login)
            .accumulate("password", password)
            .accumulate("id_client", check.idClient)
            .accumulate("price", check.price)
            .accumulate("id_server", check.idServer)


        val requestBody = RequestBody.create(
            MediaType.parse("application/json"),
            jsonData.toString()
        )
        val request = Request.Builder()
            .url("$URL/employee")
            .post(requestBody)
            .build()
        return client.newCall(request)
    }
}