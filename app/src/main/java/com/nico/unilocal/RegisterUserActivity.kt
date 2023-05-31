package com.nico.unilocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class RegisterUserActivity : AppCompatActivity() {

    private lateinit var txName :AppCompatEditText
    private lateinit var txLastname :AppCompatEditText
    private lateinit var txEmail :AppCompatEditText
    private lateinit var txPassword:AppCompatEditText
    private lateinit var txCity :AppCompatEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

         txName = findViewById(R.id.txtName)
         txLastname = findViewById(R.id.txtLastname)
         txEmail = findViewById(R.id.txtEmail)
         txPassword = findViewById(R.id.txtPassword)
         txCity = findViewById(R.id.txtCity)

        val btnInsert = findViewById<AppCompatButton>(R.id.btnInsert)


        btnInsert.setOnClickListener { saveData() }

    }

    private fun saveData(){
        var bd = DataBase(this)
        println(" ${txName.text.toString()} ")
        println(" ${txLastname.text.toString()} ")
        println(" ${txEmail.text.toString()} ")
        println(" ${txPassword.text.toString()} ")
        println(" ${txCity.text.toString()} ")

        if (txName.text.toString().isNotEmpty() || txLastname.text.toString().isNotEmpty() || txEmail.text.toString().isNotEmpty() || txPassword.text.toString().isNotEmpty() || txCity.text.toString().isNotEmpty()){
            var newUser = User(txName.text.toString(), txLastname.text.toString(), txEmail.text.toString(), txPassword.text.toString(), txCity.text.toString())
            var message = bd.insertUser(newUser)

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            if (message == "Inserted (ok)"){
                clearData()
                super.onBackPressed()
            }
        } else {
            Toast.makeText(this, "Insert all data please.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearData(){
        txName.setText("")
        txLastname.setText("")
        txEmail.setText("")
        txPassword.setText("")
        txCity.setText("")
    }

}