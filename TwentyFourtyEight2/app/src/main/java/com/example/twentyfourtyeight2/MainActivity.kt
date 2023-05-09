package com.example.twentyfourtyeight2

import android.graphics.Color
import android.os.Bundle

import android.widget.GridView

import androidx.appcompat.app.AppCompatActivity
import java.util.jar.Attributes


class MainActivity : AppCompatActivity()
{
    //private lateinit var tfe: TwentyFourtyEight
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    override fun onRestart()
    {
        super.onRestart()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

