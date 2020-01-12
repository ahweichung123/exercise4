@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.exercise4

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var dateBtn : Button? = null
    private var cal: Calendar = Calendar.getInstance()

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateBtn = findViewById(R.id.buttonDate)
        val message = findViewById<TextView>(R.id.textView1)
        var allowableInvestment = 0.0

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }

        dateBtn!!.setOnClickListener {
            DatePickerDialog(this@MainActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        buttonReset.setOnClickListener {
            recreate()
        }

        buttonCalculate.setOnClickListener {
            val myFormat = "dd-MM-yyyy"
            val sdf = SimpleDateFormat(myFormat)

            val currentDate = sdf.format(Date())
            val dob = sdf.format(cal.time)

            val now = sdf.parse(currentDate.toString())
            val before = sdf.parse(dob.toString())

            val ages = (now.time  - before.time)/1000/60/60/24/30/12
            var valid = true

            when (ages) {
                in 16..20 -> allowableInvestment = 5000.00 * 0.3
                in 21..25 -> allowableInvestment = 14000.00 * 0.3
                in 26..30 -> allowableInvestment = 29000.00 * 0.3
                in 31..35 -> allowableInvestment = 50000.00 * 0.3
                in 36..40 -> allowableInvestment = 78000.00 * 0.3
                in 41..45 -> allowableInvestment = 116000.00 * 0.3
                in 46..50 -> allowableInvestment = 165000.00 * 0.3
                in 51..55 -> allowableInvestment = 228000.00 * 0.3
                else -> valid = false
            }

            if(valid)
                message!!.text = "Age: $ages\nAllowable Investment: RM$allowableInvestment"
            else
                message!!.text = "Age: $ages\nNot applicable"

        }
    }


}