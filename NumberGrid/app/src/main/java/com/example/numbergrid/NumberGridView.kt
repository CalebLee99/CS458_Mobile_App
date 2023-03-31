package com.example.numbergrid
import android.view.View
import android.graphics.Canvas
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

class NumberGridView (context: Context?, attrs: AttributeSet?) : View(context, attrs), GestureDetector.OnGestureListener
{
    private var mWidth : Float = 0.0f
    private var mHeight : Float = 0.0f
    private var row : Int = 10
    private var col : Int = 10
    private var mTenthWidth = 0.0f
    private var mTenthHeight =0.0f

    private val rows = arrayOf<Int>(0,9)
    private val cols = arrayOf<Int>(0,9)
    private val numberGrid: Array<Array<Int>> = arrayOf(rows, cols)

    private fun populateGrid()
    {
        for(i in 0 until numberGrid.size)
        {
            for(j in 0 until numberGrid[i].size)
            {
                numberGrid[i][j] = 0
            }
        }
    }

    private fun drawLines(canvas: Canvas?)
    {
        val paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 3.0f


        for (i in 0..10)
        {
            canvas?.drawLine(0.0f, mTenthHeight * i, mWidth, mTenthHeight * i, paint)
        }

        for (j in 0 .. 10)
        {
            canvas?.drawLine(mTenthWidth * j, 0.0f,mTenthWidth * j, mHeight, paint) // Vertical
        }
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int)
    {
        super.onSizeChanged(w, h, oldw, oldh)

        mWidth = w.toFloat()
        mHeight = h.toFloat()

        mTenthWidth = mWidth / 10
        mTenthHeight = mHeight / 10
    }

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)
        val paint = Paint()
        this.populateGrid() // Populate 2D array.
        // Cut up the canvas into 10x10
        // Draw lines
        drawLines(canvas)



    }


    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent) {

    }


    override fun onSingleTapUp(e: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent) {

    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
    }

}