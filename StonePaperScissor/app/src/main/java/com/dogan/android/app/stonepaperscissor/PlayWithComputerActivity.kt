package com.dogan.android.app.stonepaperscissor

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.dogan.android.app.stonepaperscissor.databinding.ActivityPlayWithComputerBinding

class PlayWithComputerActivity : AppCompatActivity()
{
    private var binding: ActivityPlayWithComputerBinding? = null

    private var animation1: AnimationDrawable? = null
    private var animation2: AnimationDrawable? = null
    private var setTime: CountDownTimer? = null

    private var allowPlaying:Boolean = true

    private lateinit var selectionP1:String
    private lateinit var selectionP2:String

    private var scoreP1 = 0
    private var scoreP2 = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayWithComputerBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnP2Rock?.setOnClickListener {
            onPlay("rock")
        }
        binding?.btnP2Paper?.setOnClickListener {
            onPlay("paper")
        }
        binding?.btnP2Scissor?.setOnClickListener {
            onPlay("scissor")
        }
    }

    private fun playAnimation()
    {
        binding?.ivIconP1?.setImageResource(0)
        binding?.ivIconP2?.setImageResource(0)
        binding?.tvP1Status?.text = ""
        binding?.tvP2Status?.text = ""

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
                allowPlaying = true
                binding?.ivIconP1?.setBackgroundResource(0)
                binding?.ivIconP2?.setBackgroundResource(0)
                setSelectedIcon()
                setScore()
                endGame()
            }
        }.start()
    }

    private fun onPlay(selection:String)
    {
        if (allowPlaying){
            selectionP1 = listOf("rock", "paper", "scissor").random()
            selectionP2 = selection
            allowPlaying = false
            playAnimation()
        }
    }

    private fun setSelectedIcon()
    {
        when(selectionP1){
            "rock" -> binding?.ivIconP1?.setImageResource(R.drawable.rock)
            "paper" -> binding?.ivIconP1?.setImageResource(R.drawable.paper)
            "scissor" -> binding?.ivIconP1?.setImageResource(R.drawable.scissor)
        }

        when(selectionP2){
            "rock" -> binding?.ivIconP2?.setImageResource(R.drawable.rock)
            "paper" -> binding?.ivIconP2?.setImageResource(R.drawable.paper)
            "scissor" -> binding?.ivIconP2?.setImageResource(R.drawable.scissor)
        }
    }

    private fun getResult():String
    {
        return if (selectionP1 == selectionP2)
            "tie"
        else if (selectionP1 == "rock" && selectionP2 == "scissor" ||
            selectionP1 == "paper" && selectionP2 == "rock" ||
            selectionP1 == "scissor" && selectionP2 == "paper")
            "P1"
        else
            "P2"
    }

    private fun setScore()
    {
        if (getResult()=="tie")
        {
            binding?.tvP1Status?.text = "Tie"
            binding?.tvP2Status?.text = "Tie"
        }else if (getResult()=="P1"){
            binding?.tvP1Status?.text = "Win"
            binding?.tvP2Status?.text = "Lose"
            scoreP1++
            when(scoreP1){
                1->binding?.ivP1Star1?.setImageResource(R.drawable.star)
                2->binding?.ivP1Star2?.setImageResource(R.drawable.star)
                3->binding?.ivP1Star3?.setImageResource(R.drawable.star)
            }
        }else{
            binding?.tvP1Status?.text = "Lose"
            binding?.tvP2Status?.text = "Win"
            scoreP2++
            when(scoreP2){
                1->binding?.ivP2Star1?.setImageResource(R.drawable.star)
                2->binding?.ivP2Star2?.setImageResource(R.drawable.star)
                3->binding?.ivP2Star3?.setImageResource(R.drawable.star)
            }
        }
    }

    private fun endGame()
    {
        if (scoreP1 == 3 || scoreP2 == 3){
            val winner = if (scoreP1 == 3)
                "computer"
            else
                "player"

            Intent(this, FinishComputerActivity::class.java).apply {
                putExtra("winner", winner)
                startActivity(this)
            }
            finish()
        }

    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        finish()
    }
    override fun onDestroy()
    {
        super.onDestroy()
        binding = null
        setTime = null
    }
}