package com.dogan.android.app.geonames.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashActivity : AppCompatActivity()
{
    private lateinit var splashImage:ImageView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashImage = findViewById(R.id.splash_imageView)

        animateZoomOut()
    }

    private fun animateZoomOut()
    {
        splashImage.animate()
            .scaleX(0.4f)
            .scaleY(0.4f)
            .setDuration(1000L)
            .withEndAction {
                animateZoomIn()
            }
            .start()
    }

    private fun animateZoomIn()
    {
        splashImage.animate()
            .scaleX(30f)
            .scaleY(30f)
            .setDuration(500)
            .withEndAction {
                startNewActivity()
            }.start()
    }

    private fun startNewActivity()
    {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}