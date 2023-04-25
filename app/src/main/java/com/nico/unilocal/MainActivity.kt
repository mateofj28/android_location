package com.nico.unilocal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val txRegister = findViewById<TextView>(R.id.txtRegister)
        val txNickName = findViewById<AppCompatEditText>(R.id.txtNickName)
        val txpassword = findViewById<AppCompatEditText>(R.id.txtPassword)
        val btnEnter = findViewById<AppCompatButton>(R.id.btnEnter)


        txRegister.setOnClickListener {
             val intent = Intent(this, RegisterUser::class.java)
             startActivity(intent)
        }

        btnEnter.setOnClickListener { login(txNickName.text.toString() , txpassword.text.toString()) }
    }

    fun login(username: String, password: String) {
        if (username.isEmpty() && password.isEmpty() ){
            println(" error ingresa datos validos")
        } else {
            println(" Iniciando sesion ")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}