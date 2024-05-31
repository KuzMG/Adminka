package com.example.adminka.ui.sign_in

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adminka.network.KEY_LOGIN
import com.example.adminka.network.KEY_PASSWORD
import com.example.adminka.network.Manager
import com.example.adminka.network.SETTINGS
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val manager = Manager(application)
    enum class State{
        ERROR,
        LOADING,
        OK,
        START
    }
    val loadLiveData: LiveData<State>
        get() = loadMutableLiveData
    private val loadMutableLiveData = MutableLiveData<State>()
    private val setting = application.getSharedPreferences(SETTINGS, AppCompatActivity.MODE_PRIVATE)
    private val editor = setting.edit()
    init {
        loadMutableLiveData.value = State.START
    }
    fun signIn(login: String, password: String){
        loadMutableLiveData.value = State.LOADING
        manager.signIn(login, password).enqueue(object : Callback{
            override fun onFailure(request: Request, e: IOException) {
                e.printStackTrace()
                loadMutableLiveData.postValue(State.ERROR)
            }

            override fun onResponse(response: Response) {
                if(response.code() == 200){
                    editor.putString(KEY_LOGIN,login).apply()
                    editor.putString(KEY_PASSWORD,password).apply()
                    loadMutableLiveData.postValue(State.OK)
                } else{
                    loadMutableLiveData.postValue(State.ERROR)
                }
            }

        })
    }
}