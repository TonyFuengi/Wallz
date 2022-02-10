package com.paredesdev.WallzSecretSounds

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


const val EXTRA_MESSAGE = "com.paredesdev.WallzSecretSounds.TEXT_TO_ENCODE"
const val CODE_NUM = "com.paredesdev.WallzSecretSounds.NEXT_ACTIVITY_TYPE"

class Encode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.encode_activity)

        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (permissionCheck != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0);
        }
    }

    fun startEncodeGuyotActivity(view: View) {
        val message = findViewById<EditText>(R.id.editTextTextMultiLine).text.toString()

        if(message.isNotEmpty()) {
            val intent = Intent(this, EncodeGuyot::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        } else
        {
            val toast = Toast.makeText(this, "¡Debe introducir texto para poder codificarlo!", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun startEncodeParedesActivity(view: View) {
        val message = findViewById<EditText>(R.id.editTextTextMultiLine).text.toString()

        if(message.isNotEmpty()) {
            val intent = Intent(this, EncodeWallz::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        } else
        {
            val toast = Toast.makeText(this, "¡Debe introducir texto para poder codificarlo!", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun startEncodeCustomActivity(view: View) {
        val message = findViewById<EditText>(R.id.editTextTextMultiLine).text.toString()

        if(message.isNotEmpty()) {
            val intent = Intent(this, CreateKeyActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
                putExtra(CODE_NUM, 1)
            }
            startActivity(intent)
        } else
        {
            val toast = Toast.makeText(this, "¡Debe introducir texto para poder codificarlo!", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}