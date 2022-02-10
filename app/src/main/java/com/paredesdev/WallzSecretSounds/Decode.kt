package com.paredesdev.WallzSecretSounds

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Decode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.decode_activity)
    }

    fun startDecodeGuyotActivity(view: View) {
        val intent = Intent(this, DecodeGuyot::class.java)
        startActivity(intent)
    }

    fun startDecodeParedesActivity(view: View) {
        val intent = Intent(this, DecodeWallz::class.java)
        startActivity(intent)
    }

    fun startDecodeCustomActivity(view: View) {
        val intent = Intent(this, CreateKeyActivity::class.java).apply {
            putExtra(CODE_NUM, 2)
        }
        startActivity(intent)
    }
}