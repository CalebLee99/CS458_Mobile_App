package com.example.projectfinal_2048

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var game: GameLogic
    private lateinit var tiles: Array<Array<TextView?>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = GameLogic()
        game.addTile()

        tiles = Array(game.gridSize) { i ->
            Array(game.gridSize) { j ->
                findViewById<TextView>(resources.getIdentifier("tile_${i}${j}", "id", packageName))
            }
        }
        updateBoard()
    }

    private fun updateBoard() {
        val board = game.getBoard()
        for (i in 0 until game.getBoardSize()) {
            for (j in 0 until game.getBoardSize()) {
                val tileValue = board[i][j]
                val tileText = if (tileValue > 0) tileValue.toString() else ""
                tiles[i][j]?.text = tileText
            }
        }
    }
}