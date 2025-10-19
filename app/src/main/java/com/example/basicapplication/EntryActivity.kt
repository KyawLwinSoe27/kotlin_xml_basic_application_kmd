package com.example.basicapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EntryActivity : AppCompatActivity() {
    lateinit var btnBack: Button
    lateinit var txtViewDetail: TextView
    lateinit var btnEntrySubmit: Button
    lateinit var spinnerCity: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        btnBack=findViewById(R.id.btnBack)
        txtViewDetail=findViewById(R.id.txtViewDetail)
        btnEntrySubmit=findViewById(R.id.btnEntrySubmit)
        spinnerCity=findViewById(R.id.spinnerCity)

        val data=intent.extras
        var msg=data?.getString("msg")
        var id=data?.getInt("id")
        if(msg.isNullOrEmpty()){
            msg="Empty Message"
        }
        txtViewDetail.text="$msg : $id"

        btnBack.setOnClickListener {
            intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        //spinner
        val cityList=listOf("Select Your City","Yangon","Mandalay","Bago","Bagan")
        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,cityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter=adapter

        btnEntrySubmit.setOnClickListener {
            val selectedCity=spinnerCity.selectedItem.toString()
            if(selectedCity==cityList[0]){
                Toast.makeText(this,"Please Your City", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Your Selected:$selectedCity", Toast.LENGTH_LONG).show()
            }
        }



    }//on create
}//end class