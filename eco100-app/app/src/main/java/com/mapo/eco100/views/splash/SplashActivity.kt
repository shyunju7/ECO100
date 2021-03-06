package com.mapo.eco100.views.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mapo.eco100.views.MainActivity
import com.mapo.eco100.R
import com.mapo.eco100.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).asGif().load(R.raw.splash_eco100).into(binding.splashImg)

        handler.postDelayed({
            startProcess()
            finish()
        }, 2500L)

    }

    private fun startProcess() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }


}
