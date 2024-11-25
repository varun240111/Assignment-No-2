package com.example.assigment_button

import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.assigment_button.R

class MainActivity : AppCompatActivity() {

    private lateinit var myTextView: TextView
    private lateinit var textContainer: RelativeLayout

    private var isTextChanged = false
    private var isHeightChanged = false
    private var isTextColorYellow = false
    private var textPosition = TextPosition.CENTER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        myTextView = findViewById(R.id.myTextView)
        textContainer = findViewById(R.id.textContainer)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)

        // Button 1 - Toggle Text
        button1.setOnClickListener {
            myTextView.text = if (isTextChanged) "Hello" else "I am Varun Pawar"
            isTextChanged = !isTextChanged
        }

        // Button 2 - Toggle Text Size
        button2.setOnClickListener {
            myTextView.textSize = if (isHeightChanged) 18f else 24f
            isHeightChanged = !isHeightChanged
        }

        // Button 3 - Center Text
        button3.setOnClickListener {
            textPosition = TextPosition.CENTER
            updateTextPosition()
        }

        // Button 4 - Move Text Up
        button4.setOnClickListener {
            textPosition = TextPosition.UP
            updateTextPosition()
        }

        // Button 5 - Move Text Down
        button5.setOnClickListener {
            textPosition = TextPosition.DOWN
            updateTextPosition()
        }

        // Button 6 - Toggle Text Color
        button6.setOnClickListener {
            toggleTextColor()
        }
    }

    private fun updateTextPosition() {
        val params = myTextView.layoutParams as RelativeLayout.LayoutParams

        // Clear existing position rules
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0)
        params.addRule(RelativeLayout.CENTER_IN_PARENT, 0)

        when (textPosition) {
            TextPosition.CENTER -> params.addRule(RelativeLayout.CENTER_IN_PARENT)
            TextPosition.UP -> params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
            TextPosition.DOWN -> params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        }

        myTextView.layoutParams = params
        myTextView.requestLayout()
    }

    private fun toggleTextColor() {
        val color = if (isTextColorYellow) {
            ContextCompat.getColor(this, android.R.color.white)
        } else {
            ContextCompat.getColor(this, android.R.color.holo_orange_light)
        }
        myTextView.setTextColor(color)
        isTextColorYellow = !isTextColorYellow
    }

    private enum class TextPosition {
        CENTER, UP, DOWN
    }
}
