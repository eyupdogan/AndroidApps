package com.dogan.android.app.stonepaperscissor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinishActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val btnHome = findViewById<Button>(R.id.btnHome)
        val playerName:TextView = findViewById(R.id.tvPlayerName)
        playerName.text = intent.getStringExtra("name")
        btnHome.setOnClickListener {
            Intent(this, MainActivity::class.java).apply { startActivity(this) }
            finish()
        }
    }
}