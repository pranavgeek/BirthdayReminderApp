package com.example.birthdayproject
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var datePickerDialog: DatePickerDialog
    var friends = mutableListOf<Friend>()
    val storage = Storage()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storeOldData()

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{ addData() }

        val datePickerBtn = findViewById<Button>(R.id.dateBtn)
        datePickerBtn.setOnClickListener { onClick() }

        val viewFrnds = findViewById<Button>(R.id.viewFrnds)
        viewFrnds.setOnClickListener{ viewBirthDates()}

        val settingsBtn = findViewById<TextView>(R.id.settingsBtn)
        settingsBtn.setOnClickListener{goToSettings()}


        val calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                datePickerBtn.text = "$dayOfMonth/${monthOfYear + 1}/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

    }

    private fun goToSettings() {
        val Intent = Intent(this, SettingsActivity::class.java)
        startActivity(Intent)
    }

    private fun viewBirthDates() {
        val Intent = Intent(this, SecondActivity::class.java)
        startActivity(Intent)
    }

    private fun storeOldData() {
        friends = storage.getListFromSharedPreferences(this).toMutableList()
    }


    private fun onClick() {
        datePickerDialog.show()
    }

    private fun addData() {
        val nameInput = findViewById<EditText>(R.id.userInputName)
        val userLabel = findViewById<EditText>(R.id.userLabel)
        val datePickerBtn = findViewById<Button>(R.id.dateBtn)

        var nameV = nameInput.text.toString()
        var labelV = userLabel.text.toString()
        var userDate = datePickerBtn.text.toString()

        val name = nameV
        val label = labelV
        val Bdate = userDate

        val frnd = Friend(name, label, Bdate)

        friends.add(frnd)

        storage.saveListToSharedPreferences(this , friends )

        val Intent = Intent(this, SecondActivity::class.java)
        startActivity(Intent)

        Log.d("SaveBirthday", "Birthday saved successfully")
        showToast("Birthday saved successfully")
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }


}
