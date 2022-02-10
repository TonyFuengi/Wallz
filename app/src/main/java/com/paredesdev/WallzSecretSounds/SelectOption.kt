package com.paredesdev.WallzSecretSounds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SelectOption : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_option)
    }

    fun startInformationActivity(view: View) {
        val intent = Intent(this, Information::class.java).apply {}
        startActivity(intent)
    }

    fun startEncodeActivity(view: View) {
        val intent = Intent(this, Encode::class.java).apply {}
        startActivity(intent)
    }

    fun startDecodeActivity(view: View) {
        val intent = Intent(this, Decode::class.java).apply {}
        startActivity(intent)
    }
}