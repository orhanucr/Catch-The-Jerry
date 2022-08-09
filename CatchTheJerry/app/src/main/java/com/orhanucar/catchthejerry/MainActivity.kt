package com.orhanucar.catchthejerry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun play(view : View) {
        val intent = Intent(applicationContext,gameActivity :: class.java)
        startActivity(intent)
    }

    fun exit(view: View) {
        finish()
    }
}