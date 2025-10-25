package com.example.basicapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var edtText: EditText
        lateinit var radioGroup : RadioGroup
        lateinit var btnSubmit: Button
        lateinit var btnCancel: Button
        lateinit var artsCheckBox: CheckBox
        lateinit var sportsCheckBox: CheckBox
        lateinit var musicCheckBox: CheckBox
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)

        edtText = findViewById(R.id.editText)
        radioGroup = findViewById(R.id.radioGroupSection)
        btnSubmit = findViewById(R.id.btnFormSubmit)
        btnCancel = findViewById(R.id.btnFormClear)
        artsCheckBox = findViewById(R.id.arts)
        sportsCheckBox = findViewById(R.id.sports)
        musicCheckBox = findViewById(R.id.music)

        btnSubmit.setOnClickListener {

            if(edtText.text.isEmpty()) {
                edtText.error = "Please enter your name"
                return@setOnClickListener
            }

            val rdoButtonId = radioGroup.checkedRadioButtonId
            var section = "No Selected Section"

            if(rdoButtonId != -1) {
val selectedItem = findViewById<RadioButton>(rdoButtonId)

                section = "L5DC" + selectedItem.text.toString()
            }
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
            Log.d("Submit", section)

            val strBuilder = StringBuilder()
            if(artsCheckBox.isChecked) {
                strBuilder.append(artsCheckBox.text.toString() + "\n")
            }
            if(sportsCheckBox.isChecked) {
                strBuilder.append(sportsCheckBox.text.toString() + "\n")
            }
            if(musicCheckBox.isChecked) {
                strBuilder.append(musicCheckBox.text.toString() + "\n")
            }
            Toast.makeText(this, "Clubs Joined:\n$strBuilder", Toast.LENGTH_LONG).show()
            showAlert(strBuilder.toString())
        }

        btnCancel.setOnClickListener {
            edtText.text.clear()
            radioGroup.clearCheck()
            artsCheckBox.isChecked = false
            sportsCheckBox.isChecked = false
            musicCheckBox.isChecked = false
            Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showAlert(selectedClub: String) {
        val temp = if(selectedClub.isEmpty()) "No Selected Club" else selectedClub
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Joined Club")
            .setMessage(temp)
            .setCancelable(false)
            .setPositiveButton("OK") {dialog, _ ->
                Toast.makeText(this, "Clicked from OK Dialog", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
        val alertDialog = alert.create()
        alertDialog.show()
    }
}