package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var c = findViewById<Button>(R.id.contacts).setOnClickListener() {
            intent = Intent(applicationContext,ContactsActivity::class.java)
            startActivity(intent)
        }
        var a = findViewById<Button>(R.id.admin).setOnClickListener() {
            intent = Intent(applicationContext,AdminMenuActivity::class.java)
            startActivity(intent)
        }
    }
}