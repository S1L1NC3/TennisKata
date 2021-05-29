package com.dmd.tenniskata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dmd.tenniskata.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    var players = arrayOf(Player("Marcus Willis"), Player("Roger Federer"))
    var gameInstance = TennisGame(players[0], players[1])

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.imgAnimation.let { selfImageView ->
            Glide.with(this).load(R.drawable.animation).into(selfImageView)
        }

        binding.btnSimulatePlay.setOnClickListener {
            randomScore()
        }
    }

    private fun randomScore(){
        val random = Random()
        if (gameInstance.playNextRound(random.nextInt(10), random.nextInt(10))){
            binding.txtResult.let {
                binding.txtResult.text = gameInstance.currentSituation()
            }
        }
    }
}