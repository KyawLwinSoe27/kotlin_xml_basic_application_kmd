package com.example.basicapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Locale

class EntryActivity : AppCompatActivity() {
    lateinit var btnBack: Button
    lateinit var txtViewDetail: TextView
    lateinit var btnEntrySubmit: Button
    lateinit var spinnerCity: Spinner
    lateinit var txtDOB: TextView
    var selectedDOB = ""
    lateinit var txtTime: TextView
    var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        btnBack=findViewById(R.id.btnBack)
        txtViewDetail=findViewById(R.id.txtViewDetail)
        btnEntrySubmit=findViewById(R.id.btnEntrySubmit)
        spinnerCity=findViewById(R.id.spinnerCity)
        txtDOB=findViewById(R.id.txtDOB)
        txtTime = findViewById(R.id.txtTime)

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
//        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,cityList)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter= ArrayAdapter(this,R.layout.custom_spinner_item,cityList)
        adapter.setDropDownViewResource(R.layout.custom_spinner_drop_down_item)

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

        // DatePickerDialog

        txtDOB.setOnClickListener {
            showDatePickerDialog()
        }
        
        txtTime.setOnClickListener {
            showTimePickerDialog()
        }




    }//on create
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this, {_,y,m,d ->
//            selectedDOB = "$y / ${m+1} / $d"
            val c = Calendar.getInstance()
            c.set(y, m, d)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            selectedDOB = dateFormat.format(c.time)
            txtDOB.text = selectedDOB
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result = when(item.itemId){
            R.id.menu_entry->{
                val intent=Intent(this, EntryActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_about->{
                Toast.makeText(this,"About Menu Clicked", Toast.LENGTH_LONG).show()
                true
            }
            R.id.menu_logout->{
                finishAffinity()
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
        return  result
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = android.app.TimePickerDialog(this, { _, selectedHour, selectedMinute ->
//            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
//            txtTime.text = selectedTime
            val c = Calendar.getInstance()
            c.set(Calendar.HOUR_OF_DAY, selectedHour)
            c.set(Calendar.MINUTE, selectedMinute)
            val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            selectedTime = timeFormat.format(c.time)
            txtTime.text = selectedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }
}//end class