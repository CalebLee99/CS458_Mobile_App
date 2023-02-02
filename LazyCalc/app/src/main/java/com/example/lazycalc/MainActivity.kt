package com.example.lazycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var currentValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateDisplay()
    }

    fun updateDisplay() // Update Display on the calculator
    {
        val outputView = findViewById<TextView>(R.id.Button_Calculation) // Finding out where to change the text
        outputView.text = currentValue.toString()
    }

    // Common Attributes -> onClick
    fun clearPressed(view: View) // Add the view parameter to attach a function to a button
    {
        currentValue = 0;
        updateDisplay();
    }

    fun numberButtonPressed(view: View)
    {
        val button = view as Button // Convert view as button.
        val digit = button.text.toString().toInt()
        //currentValue = digit
        currentValue *= 10
        currentValue += digit
        updateDisplay()
    }
}