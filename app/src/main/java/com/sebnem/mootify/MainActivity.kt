package com.sebnem.mootify

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private lateinit var named: TextView
    private lateinit var geriSayimTextView: TextView
    private lateinit var skorTextView: TextView
    private var skor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menubutton = findViewById<ImageView>(R.id.menu)
        val musicbutton = findViewById<ImageView>(R.id.music)
        val sayacbutton = findViewById<ImageView>(R.id.sayac)
        val tickbutton = findViewById<ImageView>(R.id.tick)
        val ayarbutton = findViewById<ImageView>(R.id.ayar)
        var isMenuOpen = false

        named = findViewById(R.id.named)

        val name = intent.getStringExtra("name")
        named.text = name

        menubutton.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    if (isMenuOpen) {
                        tickbutton.visibility = View.INVISIBLE
                    } else {
                        tickbutton.visibility = View.VISIBLE
                    }
                }, 100
            )
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    if (isMenuOpen) {
                        musicbutton.visibility = View.INVISIBLE
                    } else {
                        musicbutton.visibility = View.VISIBLE
                    }
                }, 200
            )
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    if (isMenuOpen) {
                        sayacbutton.visibility = View.INVISIBLE
                    } else {
                        sayacbutton.visibility = View.VISIBLE
                    }
                }, 300
            )
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    if (isMenuOpen) {
                        isMenuOpen = false
                        ayarbutton.visibility = View.INVISIBLE
                    } else {
                        ayarbutton.visibility = View.VISIBLE
                        isMenuOpen = true
                    }
                }, 400
            )
        }

        geriSayimTextView = findViewById(R.id.gerisayim)
        skorTextView = findViewById(R.id.skor)

        val gizliValue = intent.getStringExtra("gizliValue")

        gizliValue?.let {
            val progressBar: ProgressBar = findViewById(R.id.progressBar)
            progressBar.max = it.toInt()

            val handler = Handler(Looper.getMainLooper())

            Thread {
                for (i in 1..it.toInt()) {
                    handler.post {
                        progressBar.progress = i
                        geriSayimTextView.text = "${it.toInt() - i}/${it.toInt()}"


                        skor++
                        skorTextView.text = "$skor"
                    }

                    try {
                        Thread.sleep(2000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }
    }
}







