package com.sebnem.mootify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class logogecis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logogecis)

        Handler().postDelayed({
            var gecis= Intent(this, Kayit::class.java)
            startActivity(gecis)
            finish()



        },4000)
    }
}