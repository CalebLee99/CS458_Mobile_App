package com.example.numbergrid
import android.view.View
import android.graphics.Canvas
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

class QuarteredView(context: Context?, attrs: AttributeSet?) : View(context, attrs)
{
    private var _colorSelection = arrayOf(arrayOf(0,1), arrayOf(2,3))
    private var _quadColors = arrayOf(Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA)
    private var mWidth : Float = 0.0f
    private var mHeight : Float = 0.0f
    private var mMidWidth : Float = 0.0f
    private var mMidHeight : Float = 0.0f

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
        canvas?.drawLine(mMidWidth, 0.0f,mMidWidth, mHeight, paint)

    }
}