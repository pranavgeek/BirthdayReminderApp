package com.example.birthdayproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var friends = ArrayList<Friend>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener{goBack()}

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        friends = addData() as ArrayList<Friend>
        val adapter = FriendsAdapter(friends)
        recyclerView.adapter = adapter
        adapter.onDeleteClick = {

            for(index in friends.indices) {
                if(friends[index] == it) {
                    println(it)
                    friends.removeAt(index)
                    adapter!!.notifyDataSetChanged()
                    Log.d("Delete", "Birthday deleted")
                    showToast("Deleted")
                    break
                }
            }
            val storage = Storage()
            storage.saveListToSharedPreferences(this, friends)

        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this@SecondActivity, message, Toast.LENGTH_SHORT).show()
    }


    private fun goBack() {
        val Intent = Intent(this, MainActivity::class.java)
        startActivity(Intent)
    }


    private fun addData(): List<Friend> {
        val storage = Storage()
        return storage.getListFromSharedPreferences(this )
    }
}