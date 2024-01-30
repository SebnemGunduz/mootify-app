package com.sebnem.mootify.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.sebnem.mootify.R
import com.sebnem.mootify.databinding.ActivitySettingsBinding
import com.sebnem.mootify.db.User
import com.sebnem.mootify.db.User_Table

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingsBinding
    private var userId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getLongExtra("userId", 0)
        val user = SQLite.select().from(User::class.java).where(User_Table.id.eq(userId)).querySingle()

        user?.let {
            binding.editTextEnterTimer.setText(it.time.toString())
        }

        binding.updateBtn.setOnClickListener {
            val newTime = binding.editTextEnterTimer.text.toString()

            if (newTime.isEmpty()) {
                Toast.makeText(this, "Please enter a valid time.", Toast.LENGTH_SHORT).show()
            } else {
                user?.time = newTime.toInt()
                user?.update()
                Toast.makeText(this, "Data updated.", Toast.LENGTH_SHORT).show()

            }
        }


    }

}