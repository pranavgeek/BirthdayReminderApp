package com.example.birthdayproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {
    var friends = mutableListOf<Friend>()
    val storage = Storage()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener{goBack()}

        val resetBtn = findViewById<Button>(R.id.resetBtn)
        resetBtn.setOnClickListener{resetData()}
    }

    private fun resetData() {
        storage.saveListToSharedPreferences(this, ArrayList<Friend>())
        Log.d("ResetData", "All Birthdates deleted")
        showToast("All Birthdates deleted")
    }

    private fun goBack() {
        val Intent = Intent(this, MainActivity::class.java)
        startActivity(Intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this@SettingsActivity, message, Toast.LENGTH_SHORT).show()
    }
}