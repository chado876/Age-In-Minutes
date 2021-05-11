package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(
                    this,
                    "The chosen year is $selectedYear, the month is $selectedMonth and the day is $selectedDay",
                    Toast.LENGTH_LONG
                ).show()

                val dateSelected = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                selectedDate.text = dateSelected
                selectedDate.visibility = View.VISIBLE

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val date = sdf.parse(dateSelected)

                val dateInMinutes = date!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - dateInMinutes

                ageInMinutes.text = differenceInMinutes.toString()
                ageInMinutes.visibility = View.VISIBLE

            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }
}