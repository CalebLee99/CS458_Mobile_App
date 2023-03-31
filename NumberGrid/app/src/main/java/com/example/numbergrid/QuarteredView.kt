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

class QuarteredView(context: Context?, attrs: AttributeSet?) : View(context, attrs), GestureDetector.OnGestureListener
{
    private var _colorSelection = arrayOf(arrayOf(0,1), arrayOf(2,3))
    private var _quadColors = arrayOf(Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA)
    private var mWidth : Float = 0.0f
    private var mHeight : Float = 0.0f
    private var mMidWidth : Float = 0.0f
    private var mMidHeight : Float = 0.0f

    private var mDetector = GestureDetectorCompat(this.context, this)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(mDetector.onTouchEvent(event))
        {
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int)
    {
        super.onSizeChanged(w, h, oldw, oldh)

        mWidth = w.toFloat()
        mMidWidth = (mWidth / 2).toFloat()
        mHeight = h.toFloat()
        mMidHeight = (mHeight / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)

        val paint = Paint()

        // NW
        paint.color = _quadColors[_colorSelection[0][0]]
        canvas?.drawRect(0.0f, 0.0f, mMidWidth, mMidHeight, paint)


        // NE
        paint.color = _quadColors[_colorSelection[0][1]]
        canvas?.drawRect(mMidWidth, 0.0f, mWidth, mMidHeight, paint)

        // SW
        paint.color = _quadColors[_colorSelection[1][0]]
        canvas?.drawRect(0.0f, mMidHeight, mMidWidth, mHeight, paint)

        // SW
        paint.color = _quadColors[_colorSelection[1][1]]
        canvas?.drawRect(mMidWidth, mMidHeight, mWidth, mHeight, paint)

        paint.color = Color.BLACK
        paint.strokeWidth = 3.0f
        canvas?.drawLine(0.0f, mMidHeight, mWidth, mMidHeight, paint) // Horizontal
        canvas?.drawLine(mMidWidth, 0.0f,mMidWidth, mHeight, paint) // Vertical

    }

    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent) {

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        val col = if(p0.x < mMidWidth) 0 else 1
        val row = if(p0.y < mMidHeight) 0 else 1
        _colorSelection[row][col] = (_colorSelection[row][col] + 1) % _quadColors.size
        // Need to redraw the display now!
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