package com.dogan.android.app.stonepaperscissor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlayWithOther = findViewById<Button>(R.id.btnPlayWithOther)
        btnPlayWithOther.setOnClickListener {
            Intent(this, PlayWithOtherActivity::class.java).apply {
                startActivity(this)
            }
        }

        val btnPlayWithComputer = findViewById<Button>(R.id.btnPlayWithComputer)
        btnPlayWithComputer.setOnClickListener {

        }

        val tvInstruction = findViewById<TextView>(R.id.tvInstruction)
        tvInstruction.setOnClickListener {

        }
    }
}