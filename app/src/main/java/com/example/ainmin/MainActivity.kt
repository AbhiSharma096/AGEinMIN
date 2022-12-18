package com.example.ainmin

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var SelectedDate: TextView? = null
    private var Ageinmin: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttendatepicker: Button = findViewById(R.id.datepickedbtn)
        SelectedDate= findViewById(R.id.selecteddatetv)
        Ageinmin = findViewById(R.id.ageinmintv)
        buttendatepicker.setOnClickListener{
            clickdatePicker()

        }
    }

    fun clickdatePicker(){

        val myCalander = Calendar.getInstance()
        val year =  myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
            view,year,month,day ->
                Toast.makeText(this, "Date Selected : $day/${month+1}/$year", Toast.LENGTH_LONG). show()
                var selecteddate = "$day.${month+1}.$year"
                SelectedDate?.setText(selecteddate)
                val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
                //val sdf = SimpleDateFormat("yyyy.MM.dd")
                val theDate = sdf.parse(selecteddate)
                val selectdateinmin = theDate.time/60000
                val currentDate = sdf.parse(sdf.format((System.currentTimeMillis())))
                val currentdateinmin = currentDate.time/60000
                val diefferenceinmin = currentdateinmin-selectdateinmin
                Ageinmin?.text = diefferenceinmin.toString()
        },
            year,
            month,
            day
        ).show()

        // Toast.makeText(this,"button is clicked", Toast.LENGTH_LONG).show()
    }
}