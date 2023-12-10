package com.sebnem.mootify

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Kayit : AppCompatActivity() {

    private lateinit var girisadi: EditText
    private lateinit var passwordgiris: EditText
    private lateinit var isimalEditText: EditText
    private lateinit var timeGirisEditText: EditText
    private lateinit var kayital:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit)

        val imageView: TextView = findViewById(R.id.kayital)
        timeGirisEditText = findViewById(R.id.timegiris)

        imageView.setOnClickListener {
            val userInput = timeGirisEditText.text.toString()

            val intent = Intent(this@Kayit, form::class.java)
            intent.putExtra("USER_INPUT", userInput)
            startActivity(intent)
        }

        val login: TextView = findViewById(R.id.login)
        login.setOnClickListener {
            val userInput = timeGirisEditText.text.toString()

            val intent = Intent(this@Kayit, form::class.java)
            intent.putExtra("USER_INPUT", userInput)
            startActivity(intent)
        }

        kayital = findViewById(R.id.kayital)
        kayital.setOnClickListener {
            val userInput = timeGirisEditText.text.toString()

            // Toast mesajını burada ekleyin
            Toast.makeText(this@Kayit, "Kayıt alındı. Lütfen bekleyin...", Toast.LENGTH_SHORT).show()

            // Belirli bir süre bekleyip diğer sayfaya geçmek için Handler kullanımı
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this@Kayit, form::class.java)
                intent.putExtra("USER_INPUT", userInput)
                startActivity(intent)
            }, 2000) // 2 saniye bekletme süresi (milliseconds cinsinden)
        }
    }
}
