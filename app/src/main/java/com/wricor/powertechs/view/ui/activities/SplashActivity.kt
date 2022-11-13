package com.wricor.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.wricor.powertechs.R
import com.wricor.powertechs.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        //val splashScreen = installSplashScreen()

        //splashScreen.setKeepOnScreenCondition { true }
        //val intent = Intent(this, SplashActivity::class.java)
        //startActivity(intent)
        //finish()

        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationView.setAnimation(R.raw.powertechsanim)
        binding.animationView.playAnimation()

        handler = Handler(Looper.myLooper()!!)

        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}