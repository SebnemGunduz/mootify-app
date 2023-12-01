
    package com.sebnem.mootify

    import android.app.Activity
    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import android.widget.ImageView
    import android.widget.TextView

    class form : AppCompatActivity() {

        private lateinit var isimEditText: EditText
        private lateinit var startButton: ImageView
        private lateinit var editTextNumber: EditText
        private lateinit var gizli: TextView
        private lateinit var signup: ImageView

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
                val inputNumberStr = editTextNumber.text.toString()

                if (inputNumberStr.isNotEmpty()) {
                    val inputNumber = inputNumberStr.toInt()


                    val intent = Intent(this@form, MainActivity::class.java)
                    intent.putExtra("enteredText", enteredText)
                    intent.putExtra("inputNumber", inputNumber)
                    intent.putExtra("gizliValue", gizli.text.toString())
                    startActivity(intent)
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
    }











