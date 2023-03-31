package com.example.lazycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    var currentValue = "" // Value being displayed on screen.

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
        currentValue = ""
        updateDisplay();
    }

    fun numberButtonPressed(view: View)
    {
        val button = view as Button // Convert view as button.
        val digit = button.text.toString()
        currentValue = currentValue.plus(digit)
        updateDisplay()
    }

    fun operatorButtonPressed(view: View)
    {
        val button = view as Button // Convert view as button.
        val op = button.text.toString()
        currentValue = currentValue.plus(op)
        updateDisplay()
    }

    fun calculate(view: View)
    {
        val expression = ExpressionBuilder(currentValue).build()
        val result = expression.evaluate()
        val longResult = result.toLong()
        if (result == longResult.toDouble())
        {
            currentValue = longResult.toString()
        }
        else
        {
            currentValue = result.toString()
        }
        updateDisplay()
    }

}