package com.dicoding.picodiploma.bajp2.ui.ss

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.picodiploma.bajp2.R
import com.dicoding.picodiploma.bajp2.ui.main.MainActivity

class SS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ss)

        val splashTimeOut:Long = 3000

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashTimeOut)


    }
}