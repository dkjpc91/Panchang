package com.mithilakshar.mithilapanchang

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var splashTxt: ImageView? = null
    var logo: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splashTxt = findViewById(R.id.splashTxt)
        logo = findViewById(R.id.logo)
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        animation.duration = 2500
        logo.startAnimation(animation)
        splashTxt.startAnimation(animation)
        Handler().postDelayed({
            val i = Intent(applicationContext, home::class.java)
            startActivity(i)
            finish()
        }, 2500)
    }
}