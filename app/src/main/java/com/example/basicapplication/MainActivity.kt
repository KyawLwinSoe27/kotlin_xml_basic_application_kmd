package com.example.basicapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnBack: Button
    lateinit var btnEntry: Button
    lateinit var btnChoose: Button
    lateinit var spinnerCity: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnChoose = findViewById(R.id.btnChoose)
        spinnerCity = findViewById(R.id.spinnerCity)
        btnEntry = findViewById(R.id.btnEntry)

        //Spinner
        val cities = arrayOf("Select Your City", "Yangon", "Mandalay", "Bago", "Mawlamyine")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCity.adapter = adapter

        btnChoose.setOnClickListener {
            val selectedCity = spinnerCity.selectedItem.toString()
            if(selectedCity == cities[0]){
                Toast.makeText(this,"Please choose your city", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Your, selected city is $selectedCity", Toast.LENGTH_LONG).show()
            }
        }


        btnEntry.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}