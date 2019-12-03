package com.example.on_activity_result_test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Main2Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        val count = intent.getIntExtra("COUNT", 0)
        findViewById<EditText>(R.id.text).setText("$count", TextView.BufferType.NORMAL)


        findViewById<Button>(R.id.button).setOnClickListener {

            val text = findViewById<EditText>(R.id.text).text.toString()
            val data = Intent().apply {
                putExtra("COUNT_RESULT", text.toInt())
            }
            setResult(RESULT_OK, data)
            finish()
        }
    }
}
