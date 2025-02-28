package com.edugaon.authenticationusingsqlite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val logOutButton = findViewById<Button>(R.id.logoutButton)
        logOutButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE ).edit()
            sharedPreferences.putBoolean("login_status_key", false).apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}