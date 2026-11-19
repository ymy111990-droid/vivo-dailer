import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private lateinit var number: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(24, 30, 24, 20)
        root.setBackgroundColor(Color.WHITE)

        val title = TextView(this)
        title.text = "Phone"
        title.textSize = 30f
        title.setTextColor(Color.rgb(25, 35, 55))
        title.typeface = Typeface.DEFAULT_BOLD
        root.addView(title)

        val tabs = TextView(this)
        tabs.text = "FAVORITES          RECENTS          CONTACTS"
        tabs.textSize = 12f
        tabs.setTextColor(Color.rgb(35, 115, 220))
        tabs.setPadding(0, 28, 0, 25)
        root.addView(tabs)

        number = EditText(this)
        number.hint = "Enter number"
        number.textSize = 27f
        number.gravity = Gravity.CENTER
        number.setTextColor(Color.BLACK)
        number.setSingleLine(true)
        number.inputType = 3
        number.background = null
        root.addView(
            number,
            LinearLayout.LayoutParams(
                -1, 65
            )
        )

        val keypad = arrayOf(
            arrayOf("1", "2", "3"),
            arrayOf("4", "5", "6"),
            arrayOf("7", "8", "9"),
            arrayOf("*", "0", "#")
        )

        for (row in keypad) {
            val rowLayout = LinearLayout(this)
            rowLayout.gravity = Gravity.CENTER
            rowLayout.setPadding(0, 5, 0, 5)

            for (key in row) {
                val button = Button(this)
                button.text = key
                button.textSize = 23f
                button.setTextColor(Color.rgb(35, 45, 65))
                button.background = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    setColor(Color.rgb(244, 247, 252))
                }

                button.setOnClickListener {
                    number.append(key)
                }

                rowLayout.addView(
                    button,
                    LinearLayout.LayoutParams(0, 66, 1f)
                )
            }

            root.addView(rowLayout)
        }

        val bottom = LinearLayout(this)
        bottom.gravity = Gravity.CENTER
        bottom.setPadding(0, 12, 0, 0)

        val call = Button(this)
        call.text = "☎️"
        call.textSize = 28f
        call.setTextColor(Color.WHITE)
        call.background = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(Color.rgb(25, 180, 105))
        }

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

        bottom.addView(
            call,
            LinearLayout.LayoutParams(75, 75)
        )

        val delete = TextView(this)
        delete.text = "⌫"
        delete.textSize = 30f
        delete.gravity = Gravity.CENTER
        delete.setTextColor(Color.DKGRAY)
        delete.setOnClickListener {
            val text = number.text
            if (text.isNotEmpty()) {
                text.delete(text.length - 1, text.length)
            }
        }

        bottom.addView(
            delete,
            LinearLayout.LayoutParams(75, 75)
        )

        root.addView(bottom)

        setContentView(root)
    }
}
