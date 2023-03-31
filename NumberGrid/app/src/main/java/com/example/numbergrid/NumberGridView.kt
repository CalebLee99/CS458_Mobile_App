package com.example.numbergrid
import android.view.View
import android.graphics.Canvas
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import org.w3c.dom.Text

class NumberGridView (context: Context?, attrs: AttributeSet?) : View(context, attrs), GestureDetector.OnGestureListener
{
    private var mWidth : Float = 0.0f
    private var mHeight : Float = 0.0f
    private var row : Int = 10
    private var col : Int = 10
    private var mTenthWidth = 0.0f
    private var mTenthHeight =0.0f
    private lateinit var mCanvas : Canvas

    private var mDetector = GestureDetectorCompat(this.context, this)

    private val rows = arrayOf<Int>(0,row)
    private val cols = arrayOf<Int>(0,col)
    private var numberGrid = Array(row) { IntArray(col) }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(mDetector.onTouchEvent(event))
        {
            return true
        }
        return super.onTouchEvent(event)
    }

    private fun populateGrid()
    {
        for(i in 0 .. (col - 1))
        {
            for(j in 0 .. (row - 1))
            {
                numberGrid[i][j] = 0
            }
        }
        Log.d("pop", "grid")
    }

    private fun drawLines(canvas: Canvas?)
    {
        val paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 3.0f

        paint.color = Color.CYAN
        paint.strokeWidth = 7.0f
        canvas?.drawLine(0.0f, 0.0f, mWidth, 0.0f, paint)
        canvas?.drawLine(0.0f, 0.0f, 0.0f, mHeight, paint)
        canvas?.drawLine(mWidth, 0.0f, mWidth, mHeight, paint)
        canvas?.drawLine(0.0f, mHeight, mWidth, mHeight, paint)
        paint.color = Color.BLACK
        paint.strokeWidth = 3.0f
        for (i in 0..10)
        {
            canvas?.drawLine(0.0f, mTenthHeight * i, mWidth, mTenthHeight * i, paint)
        }

        for (j in 0 .. 9)
        {
            canvas?.drawLine(mTenthWidth * j, 0.0f,mTenthWidth * j-1, mHeight, paint) // Vertical
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

    private fun drawNumbers(canvas: Canvas?)
    {
        val paint = Paint()

        paint.color = Color.RED
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 40f

        val mBounds = Rect()


        for (i in 0 .. (col-1))
        {
            for (j in 0 .. (row-1))
            {
                val number = numberGrid[i][j]
                //paint.getTextBounds(number.toString(),(mTenthWidth).toInt(), mTenthHeight.toInt(), mBounds)
                canvas?.drawText(number.toString(), mTenthWidth * (i) + 40, mTenthHeight * (j) + 80, paint)
            }

        }

    }

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)
        val paint = Paint()

        //this.populateGrid() // Populate 2D array.
        this.drawLines(canvas) // Draw the Lines for the canvas
        this.drawNumbers(canvas) // Add the numbers

    }


    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent) {

    }


    override fun onSingleTapUp(e: MotionEvent): Boolean {
        val col = e.x / mTenthWidth
        val row = e.y / mTenthHeight
        Log.v("Col Raw", col.toString())
        Log.v("Row Raw", row.toString())

        val indexCol = col.toInt()
        val indexRow = row.toInt()
        Log.v("Col Index", indexCol.toString())
        Log.v("Row Index", indexRow.toString())

        numberGrid[indexCol][indexRow] = numberGrid[indexCol][indexRow] + 1
        invalidate()
        return true
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