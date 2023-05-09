package com.example.final_2048

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import android.widget.GridView
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.math.abs

private lateinit var tiles: Array<Array<TextView?>>

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Gets the text views
        tiles = Array(4) { i ->
            Array(4) { j ->
                var tileName: String = "tile_${i}${j}"
                findViewById<TextView>(resources.getIdentifier(tileName, "id", packageName))
            }
        }
        var GH = findViewById<GridHelper>(R.id.helper)
        GH.setTile(tiles)
    }



}