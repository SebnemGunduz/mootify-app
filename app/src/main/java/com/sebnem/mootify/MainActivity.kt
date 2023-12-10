package com.sebnem.mootify

import SycActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var named: TextView
    private lateinit var geriSayimTextView: TextView
    private lateinit var skorTextView: TextView
    private var skor = 0

    private val images = intArrayOf(
        R.drawable.img_15, R.drawable.img_16, R.drawable.img_17,
        R.drawable.img_17, R.drawable.img_18, R.drawable.img_19,
        R.drawable.img_20, R.drawable.img_21, R.drawable.img_22,
        R.drawable.img_15
    )
    private val images2 = intArrayOf(
        R.drawable.img_47, R.drawable.img_48, R.drawable.img_49,
        R.drawable.img_50, R.drawable.img_51, R.drawable.img_52,
        R.drawable.img_53, R.drawable.img_54, R.drawable.img_55,
        R.drawable.img_56, R.drawable.img_57, R.drawable.img_47
    )

    private val images3 = intArrayOf(
        R.drawable.img_47, R.drawable.img_48, R.drawable.img_49,
        R.drawable.img_50, R.drawable.img_51, R.drawable.img_52,
        R.drawable.img_53, R.drawable.img_54, R.drawable.img_55,
        R.drawable.img_56, R.drawable.img_57, R.drawable.img_55
    )

    private var currentImages: IntArray = images
    private var currentIndex = 0
    private lateinit var imageViewOynat: ImageView

    private val handler = Handler(Looper.getMainLooper())
    private val ANIMATION_DELAY = 170L  // Değer artırıldı
    private val PROGRESS_ANIMATION_DELAY = 10L  // Değer düşürüldü

    private val imageRunnable = object : Runnable {
        override fun run() {
            try {
                imageViewOynat.setImageResource(currentImages[currentIndex])
                currentIndex = (currentIndex + 1) % currentImages.size
                handler.postDelayed(this, ANIMATION_DELAY)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private var progressBarHalfFilled = false

    private val progressBarRunnable = object : Runnable {
        override fun run() {
            try {
                val progressBar: ProgressBar = findViewById(R.id.progressBar)

                when {
                    progressBar.progress >= progressBar.max / 2 && !progressBarHalfFilled -> {
                        // images'dan images2'ye geçiş
                        handler.removeCallbacks(imageRunnable)
                        currentImages = images2
                        currentIndex = 0
                        progressBarHalfFilled = true  // ProgressBar yarıya kadar doldu
                        handler.postDelayed(imageRunnable, ANIMATION_DELAY)
                    }
                    progressBar.progress == progressBar.max && currentImages == images -> {
                        // images2'den images3'e geçiş
                        handler.removeCallbacks(imageRunnable)
                        currentImages = images3
                        currentIndex = 0
                        progressBarHalfFilled = false  // Sıfırla, tekrar başlaması için
                        handler.postDelayed(imageRunnable, ANIMATION_DELAY)
                    }
                }

                handler.postDelayed(this, PROGRESS_ANIMATION_DELAY)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUIComponents()
        setupMenuButton()
        setupCountdown()

        imageViewOynat = findViewById(R.id.imageViewOynat)
        imageViewOynat.setImageResource(images[currentIndex])
        handler.postDelayed(imageRunnable, ANIMATION_DELAY)

        // Gizli değeri alma ve named TextView'a set etme
        val gizliValue = intent.getStringExtra("gizliValue")
        named.text = gizliValue
    }

    override fun onDestroy() {
        handler.removeCallbacks(imageRunnable)
        handler.removeCallbacks(progressBarRunnable)
        super.onDestroy()
    }

    private fun initializeUIComponents() {
        named = findViewById(R.id.named)
        geriSayimTextView = findViewById(R.id.gerisayim)
        skorTextView = findViewById(R.id.skor)
    }

    private fun setupMenuButton() {
        val menubutton = findViewById<ImageView>(R.id.menu)
        val musicbutton = findViewById<ImageView>(R.id.music)
        val sayacbutton = findViewById<ImageView>(R.id.sayac)
        val tickbutton = findViewById<ImageView>(R.id.tick)
        val ayarbutton = findViewById<ImageView>(R.id.ayar)
        var isMenuOpen = false

        menubutton.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed(
                { tickbutton.visibility = if (isMenuOpen) View.INVISIBLE else View.VISIBLE },
                100
            )
            Handler(Looper.getMainLooper()).postDelayed(
                { musicbutton.visibility = if (isMenuOpen) View.INVISIBLE else View.VISIBLE },
                200
            )
            Handler(Looper.getMainLooper()).postDelayed(
                { sayacbutton.visibility = if (isMenuOpen) View.INVISIBLE else View.VISIBLE },
                300
            )
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    ayarbutton.visibility = if (isMenuOpen) {
                        isMenuOpen = false
                        View.INVISIBLE
                    } else {
                        isMenuOpen = true
                        View.VISIBLE
                    }
                }, 400
            )
        }

        // SayacActivity'ye geçiş ekleniyor
        sayacbutton.setOnClickListener {
            val intent = Intent(this@MainActivity,SycActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupCountdown() {
        val gizliValue = intent.getStringExtra("gizliValue")

        gizliValue?.let {
            val progressBar: ProgressBar = findViewById(R.id.progressBar)
            progressBar.max = it.toInt()

            Thread {
                for (i in 1..it.toInt()) {
                    handler.post {
                        progressBar.progress = i
                        geriSayimTextView.text = "${it.toInt() - i}/${it.toInt()}"
                        skor++
                        skorTextView.text = "$skor"
                    }

                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    if (i == it.toInt()) {

                        handler.postDelayed(progressBarRunnable, PROGRESS_ANIMATION_DELAY)
                    }
                }
            }.start()
        }
    }
}