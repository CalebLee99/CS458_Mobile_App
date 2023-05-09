package com.example.final_2048

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs

class GridHelper (context: Context?, attrs: AttributeSet?) : View(context, attrs), GestureDetector.OnGestureListener
{
    private var mDetector = GestureDetectorCompat(this.context, this)
    private val SWIPE_THRESHOLD = 50
    private val game : GameLogic = GameLogic()
    private lateinit var tiles: Array<Array<TextView?>>

    override fun onDraw(canvas: Canvas?) {
        Log.v("Create", "Created")
        game.addTile()
        game.addTile()
        Log.v("Created a ", game.getBoardSize().toString())

        updateBoard()
    }

    public fun setTile(inputTiles: Array<Array<TextView?>>)
    {
        tiles = inputTiles
    }


    private fun updateBoard()
    {
        val board = game.getBoard()
        for (i in 0 until game.getBoardSize())
        {
            for (j in 0 until game.getBoardSize()) {
                val tileValue = board[i][j]
                val tileText = if (tileValue > 0) tileValue.toString() else "0"
                tiles[i][j]?.text = tileText
                tiles[i][j]?.setTextColor(getTileColor(tileValue))
                tiles[i][j]?.textSize = 100 / tileText.length.toFloat()
            }
        }
    }



    private fun getTileColor(value: Int): Int {
        return when (value) {
            0 -> Color.parseColor("#C0C0C0")
            2 -> Color.parseColor("#EEE4DA")
            4 -> Color.parseColor("#EDE0C8")
            8 -> Color.parseColor("#F2B179")
            16 -> Color.parseColor("#F59563")
            32 -> Color.parseColor("#F67C5F")
            64 -> Color.parseColor("#F65E3B")
            128 -> Color.parseColor("#EDCF72")
            256 -> Color.parseColor("#EDCC61")
            512 -> Color.parseColor("#EDC850")
            2048 -> Color.parseColor("#EDC22E")
            else -> Color.BLACK

        }
    }



    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(mDetector.onTouchEvent(event))
        {
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent) {

    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return true
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {

    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {

        Log.v("Fling", "Swipe")
        val diffX = e2?.x?.minus(e1?.x ?: 0f) ?: 0f
        val diffY = e2?.y?.minus(e1?.y ?: 0f) ?: 0f
        if (abs(diffX) > abs(diffY))
        {
            if (abs(diffX) > SWIPE_THRESHOLD)
            {
                if (diffX > 0)
                {
                    // Handle right swipe
                    Log.v("Swipe", "Right")
                    game.move(GameLogic.Direction.RIGHT)
                    game.addTile()
                    updateBoard()

                }
                else
                {
                    // Handle left swipe
                    Log.v("Swipe", "Left")
                    game.move(GameLogic.Direction.LEFT)
                    game.addTile()
                    updateBoard()
                }
                return true
            }
        }
        else
        {
            if (abs(diffY) > SWIPE_THRESHOLD)
            {
                if (diffY > 0)
                {
                    // Handle down swipe
                    Log.v("Swipe", "Down")
                    game.move(GameLogic.Direction.DOWN)
                    game.addTile()
                    updateBoard()
                }
                else
                {
                    // Handle up swipe
                    Log.v("Swipe", "Up")
                    game.move(GameLogic.Direction.UP)
                    game.addTile()
                    updateBoard()
                }
                return true
            }
        }
        return false
    }
}