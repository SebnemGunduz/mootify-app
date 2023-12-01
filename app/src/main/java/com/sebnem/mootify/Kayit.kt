package com.sebnem.mootify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView


class Kayit : AppCompatActivity() {

    private lateinit var girisadi: EditText
    private lateinit var passwordgiris: EditText
    private lateinit var isimalEditText: EditText
    private lateinit var timeGirisEditText: EditText
    private lateinit var kayital: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit)

        val imageView: ImageView = findViewById(R.id.kayital)
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


            val intent = Intent(this@Kayit, form::class.java)
            intent.putExtra("USER_INPUT", userInput)
            startActivity(intent)
        }
    }
}







