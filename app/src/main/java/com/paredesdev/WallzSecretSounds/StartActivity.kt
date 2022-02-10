package com.paredesdev.WallzSecretSounds

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        Handler().postDelayed(
                {
                    val intent = Intent(this, SelectOption::class.java).apply {}
                    startActivity(intent)
                    finish()
                },
                3000 // value in milliseconds
        )
    }

    fun startApp(view: View) {
        val intent = Intent(this, SelectOption::class.java).apply {}
        startActivity(intent)
    }
}