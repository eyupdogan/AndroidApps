package com.dogan.android.app.stonepaperscissor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import pl.droidsonroids.gif.GifImageView

class FinishComputerActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_computer)

        val winner = intent.getStringExtra("winner")
        setWinner(winner.toString())
        val btnHome = findViewById<Button>(R.id.btnHome)
        btnHome.setOnClickListener {
            finish()
        }
    }

    private fun setWinner(winner:String)
    {
        val imageView:GifImageView = findViewById(R.id.statusDisplay)
        val tvStatus:TextView = findViewById(R.id.tvStatus)
        if (winner == "computer"){
            imageView.setImageResource(R.drawable.lose_gif)
            tvStatus.text = "Better luck next time\nYou lose!"
        }else{
            imageView.setImageResource(R.drawable.win_gif)
            tvStatus.text = "Congratulations\nYou won!"
        }
    }
}