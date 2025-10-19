package com.example.basicapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var radioGroup : RadioGroup
        lateinit var btnSubmit: Button
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)

        radioGroup = findViewById(R.id.radioGroupSection)
        btnSubmit = findViewById(R.id.btnFormSubmit)

        btnSubmit.setOnClickListener {
            val rdoButtonId = radioGroup.checkedRadioButtonId
            var section = "No Selected Section"

            if(rdoButtonId != -1) {
val selectedItem = findViewById<RadioButton>(rdoButtonId)

                section = "L5DC" + selectedItem.text.toString()
            }
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
            Log.d("Submit", section)
        }
    }
}