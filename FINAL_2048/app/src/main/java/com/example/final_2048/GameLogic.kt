package com.example.final_2048

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs

class GameLogic
{
    public val gridSize = 4
    private val board = Array(gridSize) {IntArray (gridSize) }

    // Adds a new tile to the board.
    fun addTile()
    {
        // Find an empty space on the board
        val emptySpaces = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until gridSize)
        {
            for (j in 0 until gridSize)
            {
                if (board[i][j] == 0)
                {
                    emptySpaces.add(Pair(i, j))
                }
            }
        }

        if (emptySpaces.isNotEmpty())
        {
            val (i, j) = emptySpaces.random()
            board[i][j] = if (Math.random() < 0.9) 2 else 4
        }
        else
        {
            //Game Over
        }
    }

    // Move the tiles on the board
    // Getting an error when two tiles merge at the same time. Index out of bounds.
    fun move(direction: Direction) {
        for (i in 0 until gridSize) {
            val tiles = mutableListOf<Int>()
            for (j in 0 until gridSize) {
                val tile = getTile(direction, i, j)

                if (tile != 0) {
                    tiles.add(tile)
                }
            }

            val mergedTiles = mergeTiles(tiles)

            for (j in 0 until gridSize) {
                val tile = if (j < mergedTiles.size) mergedTiles[j] else 0
                setTile(direction, i, j, tile)
            }
        }
    }

    // Merge adjacent tiles
    private fun mergeTiles(tiles: MutableList<Int>): MutableList<Int> {
        val mergedTiles = mutableListOf<Int>()
        var skipNext = false

        for (i in 0 until tiles.size) {
            if (skipNext) {
                skipNext = false
                continue
            }

            if (i < tiles.size - 1 && tiles[i] == tiles[i + 1]) {
                mergedTiles.add(tiles[i] * 2)
                skipNext = true
            } else {
                mergedTiles.add(tiles[i])
                skipNext = false
            }
        }

        return mergedTiles
    }


    // Get the tile value in the given direction
    private fun getTile(direction: Direction, i: Int, j: Int): Int
    {
        return when (direction) {
            Direction.UP -> board[j][i]
            Direction.DOWN -> board[gridSize - j - 1][i]
            Direction.LEFT -> board[i][j]
            Direction.RIGHT -> board[i][gridSize - j - 1]
        }
    }

    // Set the tile value in the given direction
    private fun setTile(direction: Direction, i: Int, j: Int, value: Int)
    {
        when (direction) {
            Direction.UP -> board[j][i] = value
            Direction.DOWN -> board[gridSize - j - 1][i] = value
            Direction.LEFT -> board[i][j] = value
            Direction.RIGHT -> board[i][gridSize - j - 1] = value
        }
    }

    public fun getBoard(): Array<IntArray> {
        return this.board;
    }

    public fun getBoardSize(): Int {
        return this.gridSize
    }

    // Define the direction enum
    enum class Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}