package com.sebnem.mootify

import com.sebnem.mootify.MainActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class form : AppCompatActivity() {

    private lateinit var isimEditText: EditText
    private lateinit var startButton: TextView
    private lateinit var editTextNumber: EditText
    private lateinit var gizli: TextView
    private lateinit var signup: TextView
    private val correctPassword = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        isimEditText = findViewById(R.id.isimal)
        editTextNumber = findViewById(R.id.sifreal)
        startButton = findViewById(R.id.startbutton)
        gizli = findViewById(R.id.gizli)
        signup = findViewById(R.id.signup)

        startButton.setOnClickListener {
            val enteredText = isimEditText.text.toString()
            val inputPassword = editTextNumber.text.toString()

            if (inputPassword == correctPassword) {
                showToastAndNavigate(enteredText, gizli.text.toString())
            } else {

                Toast.makeText(this@form, "Yanlış şifre! Tekrar deneyin.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val userInput = intent.getStringExtra("USER_INPUT")
        userInput?.let {
            gizli.text = "$it"
        }

        signup.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showToastAndNavigate(enteredText: String, gizliValue: String) {
        val toast = Toast.makeText(this@form, "Kullanıcı Doğru! Bekleyin...", Toast.LENGTH_SHORT)
        toast.show()


        Handler().postDelayed({
            val intent = Intent(this@form, MainActivity::class.java)
            intent.putExtra("enteredText", enteredText)
            intent.putExtra("gizliValue", gizliValue)
            startActivity(intent)
        }, 2000)
    }
}