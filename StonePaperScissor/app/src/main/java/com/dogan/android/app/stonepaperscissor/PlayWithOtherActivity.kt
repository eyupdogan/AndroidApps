package com.dogan.android.app.stonepaperscissor

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.dogan.android.app.stonepaperscissor.databinding.ActivityPlayWithOtherBinding

class PlayWithOtherActivity : AppCompatActivity()
{
    private var binding:ActivityPlayWithOtherBinding? = null

    private var animation1:AnimationDrawable? = null
    private var animation2:AnimationDrawable? = null
    private var setTime:CountDownTimer? = null

    private var player1Ready:Boolean = false
    private var player2Ready:Boolean = false
    private var allowPlaying:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayWithOtherBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnP2Rock?.setOnClickListener {
            playAnimation()
        }
    }

    private fun playAnimation()
    {
        binding?.ivIconP1?.setBackgroundResource(R.drawable.animation_rps)
        animation1 = binding?.ivIconP1?.background as AnimationDrawable

        binding?.ivIconP2?.setBackgroundResource(R.drawable.animation_rps)
        animation2 = binding?.ivIconP2?.background as AnimationDrawable

        setTime = object :CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long)
            {
                animation1?.start()
                animation2?.start()
            }

            override fun onFinish()
            {
                animation1?.stop()
                animation2?.stop()
            }
        }.start()
    }
}