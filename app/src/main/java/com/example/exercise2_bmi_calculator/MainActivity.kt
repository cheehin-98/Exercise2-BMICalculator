package com.example.exercise2_bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var calculateBMI:Double = 0.0
    lateinit var viewBMIPicture: ImageView
    lateinit var  totalBMI: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewBMIPicture  = findViewById(R.id.imageViewProfile)
        totalBMI = findViewById(R.id.textViewBMI)

        val btnCalculate: Button = findViewById(R.id.buttonCalculate)
        btnCalculate.setOnClickListener { setDrawableAndBMI() }

        val btnClear: Button = findViewById(R.id.buttonReset)
        btnClear.setOnClickListener { Reset() }
    }
    private fun Reset()
    {
        val textWeight: TextView = findViewById(R.id.editTextWeight)
        val textHeight: TextView = findViewById(R.id.editTextHeight)
        totalBMI = findViewById(R.id.textViewBMI)
        textHeight.text = ""
        textWeight.text = ""
        totalBMI.text = "BMI: "
        viewBMIPicture.setImageResource(R.drawable.empty)
        calculateBMI = 0.0

    }
    private fun calculateBMI(): Int {


        try {
            val textWeight: TextView = findViewById(R.id.editTextWeight)
            val textHeight: TextView = findViewById(R.id.editTextHeight)

            val weight = textWeight.text.toString()
            val height = textHeight.text.toString()

            calculateBMI = weight.toDouble() / ((height.toDouble()/ 100 ) * 2)

            totalBMI.text = ("BMI: " + calculateBMI)

        }
        catch (e: Exception) {
            totalBMI.text = "BMI: "
            Toast.makeText(this, "Please enter your weight and height",
                Toast.LENGTH_SHORT).show()
        }

        return when (calculateBMI)
        {
            0.0 -> R.drawable.empty
            in Double.MIN_VALUE..18.5 -> R.drawable.under
            in Double.MIN_VALUE..24.9 -> R.drawable.normal
            else  -> R.drawable.over
        }
    }
    private fun setDrawableAndBMI()
    {

        viewBMIPicture.setImageResource(calculateBMI())
    }
}
