package com.toppr.sampletv

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import java.lang.ref.WeakReference

class SelectClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_class)
        val gridLayout = findViewById<GridLayout>(R.id.grid_layout)

        for (i in 1..8) {
            val button = Button(this)
            button.text = when (i) {
                1 -> getString(R.string._1st)
                2 -> getString(R.string._2nd)
                3 -> getString(R.string._3rd)
                4 -> getString(R.string._4th)
                5 -> getString(R.string._5th)
                6 -> getString(R.string._6th)
                7 -> getString(R.string._7th)
                else -> getString(R.string._8th)
            }

            val param = GridLayout.LayoutParams(
                GridLayout.spec(
                    GridLayout.UNDEFINED, GridLayout.FILL, 0.1f
                ),
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 0.1f)
            )
            param.height = 200
            param.width = 200

            button.setOnKeyListener { view, index, keyEvent ->
                val handled = false
                if (keyEvent.action == KeyEvent.ACTION_DOWN && keyEvent.keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                    //Toast.makeText(this, "You selected Grade $i", Toast.LENGTH_SHORT).show()
                    HomeActivity.startActivity(WeakReference(this@SelectClassActivity))
                }
                handled
            }

            gridLayout.addView(button)

        }

    }

    companion object {
        fun startActivity(weakReference: WeakReference<Activity>) {
            val intent = Intent(weakReference.get(), SelectClassActivity::class.java)
            weakReference.get()?.startActivity(intent)
        }
    }
}