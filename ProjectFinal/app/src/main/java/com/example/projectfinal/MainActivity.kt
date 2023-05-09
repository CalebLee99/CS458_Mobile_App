package com.example.projectfinal

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val boardSize = 4
    private val board = Array(boardSize) { IntArray(boardSize) }
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var scoreText: TextView

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridLayout = findViewById<GridView>(R.id.activity_main)
        buttons = Array(boardSize) { row ->
            Array(boardSize) { col ->
                val button = Button(this)
                button.text = ""
                button.setBackgroundColor(Color.GRAY)
                button.setTextColor(Color.WHITE)
                button.textSize = 24f
                button.gravity = Gravity.CENTER
                val params = GridLayout.LayoutParams()
                params.width = 0
                params.height = GridLayout.LayoutParams.WRAP_CONTENT
                params.columnSpec = GridLayout.spec(col, 1f)
                params.rowSpec = GridLayout.spec(row, 1f)
                button.layoutParams = params
                gridLayout.addView(button)
                button
            }
        }

        initializeGame()
    }

    private fun initializeGame() {
        // Set up the initial game board
        addRandomNumber()
        addRandomNumber()

        updateGameBoard()
    }

    private fun updateGameBoard() {
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                val button = buttons[row][col]
                val value = board[row][col]
                button.text = if (value == 0) "" else value.toString()
                button.setBackgroundColor(getColorForNumber(value))
            }
        }

        scoreText.text = "Score: $score"
    }

    private fun addRandomNumber() {
        var row: Int
        var col: Int
        do {
            row = Random.nextInt(boardSize)
            col = Random.nextInt(boardSize)
        } while (board[row][col] != 0)

        board[row][col] = if (Random.nextInt(10) == 0) 4 else 2
    }

    private fun getColorForNumber(number: Int): Int {
        return when (number) {
            0 -> Color.GRAY
            2 -> Color.parseColor("#EEE4DA")
            4 -> Color.parseColor("#EDE0C8")
            8 -> Color.parseColor("#F2B179")
            16 -> Color.parseColor("#F59563")
            32 -> Color.parseColor("#F67C5F")
            64 -> Color.parseColor("#F65E3B")
            128 -> Color.parseColor("#EDCF72")
            256 -> Color.parseColor("#EDCC61")
            512 -> Color.parseColor("#EDC850")
            1024 -> Color.parseColor("#EDC53F")
            2048 -> Color.parseColor("#EDC22E")
            else -> Color.BLACK
        }
    }

    private fun isGameOver(): Boolean
    {
        // Check if the board is full
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                if (board[row][col] == 0) {
                    return false
                }
            }
        }
        // Check if there are any adjacent matching numbers
        for (row in 0 until boardSize)
        {
            for (col in 0 until boardSize) {
                val value = board[row][col]
                if (row > 0 && board[row - 1][col] == value) {
                    return false
                }
                if (row < boardSize - 1 && board[row + 1][col] == value) {
                    return false
                }
                if (col > 0 && board[row][col - 1] == value) {
                    return false
                }
                if (col < boardSize - 1 && board[row][col + 1] == value) {
                    return false
                }
            }
        }
        return true
    }

    override fun onRestart() {
        super.onRestart()
        initializeGame()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (
                    android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                            or android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }

    }
}