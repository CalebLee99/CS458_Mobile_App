package com.example.twentyfourtyeight2

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import android.widget.*
import androidx.core.view.drawToBitmap
import org.w3c.dom.Text
import kotlin.random.Random

class TwentyFourtyEight(context: Context?, attrs: AttributeSet?) : View(context, attrs), GestureDetector.OnGestureListener
{
    public val boardSize = 4
    private val board = Array(boardSize) { IntArray(boardSize) }
    private var gridView: GridView
    private lateinit var buttons: Array<Array<Button>>
    public var scoreText: TextView
    public lateinit var restartButton: Button
    private var mWidth : Float = 0.0f
    private var mHeight : Float = 0.0f
    private var cellWidth : Int = 0
    private var cellHeight : Int = 0


    private var xDown = 0f
    private var yDown = 0f
    private var xUp = 0f
    private var yUp = 0f
    private var score = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int)
    {
        super.onSizeChanged(w, h, oldw, oldh)

        mWidth = w.toFloat()
        mHeight = h.toFloat()

    }

    init {
        gridView = GridView(this.context)
        scoreText = TextView(this.context)
        val cellWidth = mWidth / boardSize
        val cellHeight = mHeight / boardSize

        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    }
    private fun initializeGame()
    {
        // Set up the initial game board
        addRandomNumber()
        addRandomNumber()

        updateGameBoard()
    }

    private fun updateGameBoard()
    {
        for (row in 0 until boardSize)
        {
            for (col in 0 until boardSize)
            {
                val value = board[row][col]
            }
        }

        scoreText.text = "Score: $score"
    }

    private fun addRandomNumber()
    {
        var row: Int
        var col: Int
        do
        {
            row = Random.nextInt(boardSize)
            col = Random.nextInt(boardSize)
        } while (board[row][col] != 0)

        board[row][col] = if (Random.nextInt(10) == 0) 4 else 2
    }

    override fun onTouchEvent(event: MotionEvent): Boolean
    {

        when (event.action)
        {
            MotionEvent.ACTION_DOWN ->
            {
                xDown = event.x
                yDown = event.y
            }
            MotionEvent.ACTION_UP ->
            {
                xUp = event.x
                yUp = event.y
                val deltaX = xUp - xDown
                val deltaY = yUp - yDown
                if (Math.abs(deltaX) > Math.abs(deltaY))
                {
                    // Swipe left or right
                    if (Math.abs(deltaX) > SWIPE_THRESHOLD)
                    {
                        if (deltaX > 0)
                        {
                            // Swipe right
                            swipeRight()
                        } else
                        {
                            // Swipe left
                            swipeLeft()
                        }
                    }
                } else
                {
                    // Swipe up or down
                    if (Math.abs(deltaY) > SWIPE_THRESHOLD)
                    {
                        if (deltaY > 0)
                        {
                            // Swipe down
                            swipeDown()
                        } else
                        {
                            // Swipe up
                            swipeUp()
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun swipeLeft() {
        var moved = false
        for (i in 0 until boardSize)
        {
            var mergeStart = 0
            for (j in 1 until boardSize)
            {
                if (board[i][j] == 0) continue
                if (board[i][j] == board[i][mergeStart])
                {
                    board[i][mergeStart] *= 2
                    board[i][j] = 0
                    score += board[i][mergeStart]
                    moved = true
                } else
                {
                    if (board[i][mergeStart] == 0)
                    {
                        board[i][mergeStart] = board[i][j]
                        board[i][j] = 0
                        moved = true
                    }
                    mergeStart = j
                }
            }
        }
        if (moved)
        {
            addRandomNumber()
        }
        updateGameBoard()
        isGameOver()
    }

    private fun swipeRight() {
        // Implement right swipe logic here
    }

    private fun swipeUp() {
        // Implement up swipe logic here
    }

    private fun swipeDown() {
        // Implement down swipe logic here
    }

    companion object {
        private const val SWIPE_THRESHOLD = 100
    }


    private fun getColorForNumber(number: Int): Int
    {
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

    private fun isGameOver(): Boolean {
        // Check if the board is full
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                if (board[row][col] == 0) {
                    return false
                }
            }
        }

        for (row in 0 until boardSize)
        {
            for (col in 0 until boardSize)
            {
                val value = board[row][col]
                if (row > 0 && board[row - 1][col] == value)
                {
                    return false
                }
                if (row < boardSize - 1 && board[row + 1][col] == value)
                {
                    return false
                }
                if (col > 0 && board[row][col - 1] == value)
                {
                    return false
                }
                if (col < boardSize - 1 && board[row][col + 1] == value)
                {
                    return false
                }
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val cellWidth = mWidth / boardSize
        val cellHeight = mHeight / boardSize
        initializeGame()


        canvas?.apply {
            val buttonWidth = width / boardSize
            val buttonHeight = height / boardSize

            // Draw the buttons to the canvas
            for (row in 0 until boardSize) {
                for (col in 0 until boardSize) {
                    val button = buttons[row][col]
                    val customButton = CustomButton(context, button.text as String)
                    customButton.setTextSize(24.toFloat())
                    customButton.setBounds(button.x, button.y, 200.toFloat(), 200.toFloat())
                    customButton.draw(canvas)
                }
            }
        }


        val paint = Paint()
        paint.textSize = 100f // 24 is the font size in pixels


    }

    override fun onDown(e: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onShowPress(e: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLongPress(e: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        TODO("Not yet implemented")
    }

    class CustomButton(val context: Context, val text: String) {
        private val paint = Paint()
        private val bounds = RectF()
        private var x = 0f
        private var y = 0f
        private var width = 0f
        private var height = 0f
        private var textSize = 0f

        init {
            paint.isAntiAlias = true
            paint.color = Color.GRAY
            paint.textAlign = Paint.Align.CENTER
        }

        fun setTextSize(size: Float) {
            textSize = size
            paint.textSize = textSize
        }

        fun setBounds(x: Float, y: Float, width: Float, height: Float) {
            this.x = x
            this.y = y
            this.width = width
            this.height = height
            bounds.set(x, y, x + width, y + height)
        }

        fun draw(canvas: Canvas) {
            paint.style = Paint.Style.FILL
            canvas.drawRoundRect(bounds, 10f, 10f, paint)
            paint.color = Color.WHITE
            paint.style = Paint.Style.STROKE
            canvas.drawRoundRect(bounds, 10f, 10f, paint)
            paint.style = Paint.Style.FILL
            canvas.drawText(text, x + width / 2, y + height / 2 + textSize / 2, paint)
        }
    }
}