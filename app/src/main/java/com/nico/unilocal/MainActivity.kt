package com.nico.unilocal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {

    private var bd = DataBase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val txRegister = findViewById<TextView>(R.id.txtRegister)
        val txUsername = findViewById<AppCompatEditText>(R.id.txtUsername)
        val txpassword = findViewById<AppCompatEditText>(R.id.txtPassword)
        val btnEnter = findViewById<AppCompatButton>(R.id.btnEnter)


        txRegister.setOnClickListener {
             val intent = Intent(this, RegisterUserActivity::class.java)
             startActivity(intent)
        }

        btnEnter.setOnClickListener { login(txUsername.text.toString() , txpassword.text.toString()) }
    }

    fun login(Username: String, password: String) {
        if (Username.isEmpty() && password.isEmpty() ){
            Toast.makeText(this, "Email and Password are required", Toast.LENGTH_SHORT).show()
        } else {

            println(" login ")
            var res = bd.findCredentialUser(Username, password)
            println("res: $res")
            if (res) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }


        }
    }
}