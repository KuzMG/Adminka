package com.example.adminka.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminka.R
import com.example.adminka.ui.sign_in.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view,LoginFragment())
                .commit()
    }
}