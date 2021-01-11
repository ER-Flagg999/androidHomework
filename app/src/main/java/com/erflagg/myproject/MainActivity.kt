package com.erflagg.myproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtons()
    }

    private fun initButtons() {

        button_menu.setOnClickListener {
            Toast.makeText(this, R.string.btn_menu, Toast.LENGTH_SHORT).show()
        }

        button_fav.setOnClickListener {
            Toast.makeText(this, R.string.btn_fav, Toast.LENGTH_SHORT).show()
        }

        button_selections.setOnClickListener {
            Toast.makeText(this, R.string.btn_selections, Toast.LENGTH_SHORT).show()
        }

        button_settings.setOnClickListener {
            Toast.makeText(this, R.string.btn_setting, Toast.LENGTH_SHORT).show()
        }

        button_watch_later.setOnClickListener {
            Toast.makeText(this, R.string.btn_watch_later, Toast.LENGTH_SHORT).show()
        }
    }
}