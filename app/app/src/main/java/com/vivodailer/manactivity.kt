package com.vivodialer

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private lateinit var number: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(24, 35, 24, 24)
        layout.setBackgroundColor(Color.WHITE)

        val title = TextView(this)
        title.text = "Phone"
        title.textSize = 30f
        title.setTextColor(Color.BLACK)
        layout.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Contacts     Recent     Favorites"
        subtitle.textSize = 15f
        subtitle.setTextColor(Color.GRAY)
        layout.addView(subtitle)

        number = EditText(this)
        number.hint = "Enter phone number"
        number.textSize = 25f
        number.gravity = Gravity.CENTER
        number.inputType = 3
        layout.addView(number)

        val keys = arrayOf(
            arrayOf("1", "2", "3"),
            arrayOf("4", "5", "6"),
            arrayOf("7", "8", "9"),
            arrayOf("*", "0", "#")
        )

        for (row in keys) {
            val rowLayout = LinearLayout(this)
            rowLayout.gravity = Gravity.CENTER

            for (key in row) {
                val button = Button(this)
                button.text = key
                button.textSize = 24f
                button.setOnClickListener {
                    number.append(key)
                }

                rowLayout.addView(
                    button,
                    LinearLayout.LayoutParams(0, 70, 1f)
                )
            }

            layout.addView(rowLayout)
        }

        val call = Button(this)
        call.text = "📞  CALL"
        call.textSize = 20f
        call.setBackgroundColor(Color.rgb(30, 170, 100))
        call.setTextColor(Color.WHITE)

        call.setOnClickListener {
            val phone = number.text.toString()
            if (phone.isNotEmpty()) {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:$phone")
                )
                startActivity(intent)
            }
        }

        layout.addView(call)

        setContentView(layout)
    }
}
