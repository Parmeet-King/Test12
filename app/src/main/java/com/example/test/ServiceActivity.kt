package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        val button1 = findViewById<Button>(R.id.button1)

        button1.setOnClickListener {
            intent = Intent(applicationContext,TestService::class.java)
            startService(intent)
        }
    }
}