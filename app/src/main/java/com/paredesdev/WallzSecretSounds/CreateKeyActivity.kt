package com.paredesdev.WallzSecretSounds

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

const val KEY_ARRAY = "com.paredesdev.WallzSecretSounds.CUSTOM_KEY_ARRAY"

class CreateKeyActivity : AppCompatActivity()
{
    var message = ""
    var code = 0 // 1=Encode 2=Decode

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_key_activity)
        code = intent.getIntExtra(CODE_NUM, 0)

        if(code == 1)
        {
            message = intent.getStringExtra(EXTRA_MESSAGE) as String
        }
    }

    fun CreateCustomKey (view: View)
    {
        var key = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        //var key = intArrayOf(1, 3, 4, 6, 8, 9, 11, 13, 15, 16, 18, 20, 21, 23, 25, 27, 28, 30, 32, 33, 35, 37, 39, 40, 42, 44, 45, 47, 49, 51, 52, 54, 56, 57, 59, 61, 63, 64, 66, 68, 69, 71)


        //region Lectura Caracteres

        //region Caracter 1
        var noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar1).selectedItem as String
        var octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar1).selectedItem as String
        var durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar1).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[0] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 2
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar2).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar2).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar2).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[1] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 3
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar3).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar3).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar3).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[2] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 4
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar4).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar4).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar4).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[3] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 5
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar5).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar5).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar5).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[4] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 6
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar6).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar6).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar6).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[5] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 7
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar7).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar7).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar7).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[6] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 8
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar8).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar8).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar8).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[7] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 9
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar9).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar9).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar9).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[8] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 10
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar10).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar10).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar10).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[9] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 11
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar11).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar11).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar11).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[10] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 12
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar12).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar12).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar12).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[11] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 13
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar13).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar13).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar13).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[12] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 14
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar14).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar14).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar14).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[13] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 15
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar15).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar15).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar15).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[14] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 16
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar16).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar16).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar16).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[15] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 17
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar17).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar17).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar17).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[16] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 18
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar18).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar18).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar18).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[17] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 19
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar19).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar19).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar19).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[18] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 20
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar20).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar20).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar20).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[19] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 21
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar21).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar21).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar21).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[20] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 22
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar22).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar22).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar22).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[21] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 23
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar23).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar23).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar23).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[22] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 24
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar24).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar24).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar24).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[23] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 25
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar25).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar25).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar25).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[24] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 26
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar26).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar26).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar26).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[25] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 27
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar27).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar27).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar27).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[26] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 28
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar28).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar28).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar28).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[27] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 29
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar29).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar29).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar29).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[28] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 30
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar30).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar30).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar30).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[29] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 31
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar31).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar31).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar31).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[30] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 32
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar32).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar32).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar32).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[31] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 33
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar33).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar33).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar33).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[32] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 34
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar34).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar34).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar34).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[33] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 35
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar35).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar35).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar35).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[34] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 36
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar36).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar36).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar36).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[35] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 37
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar37).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar37).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar37).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[36] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 38
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar38).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar38).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar38).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[37] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 39
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar39).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar39).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar39).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[38] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 40
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar40).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar40).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar40).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[39] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 41
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar41).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar41).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar41).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[40] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //region Caracter 42
        noteSpinner = findViewById<Spinner>(R.id.spinnerNoteChar42).selectedItem as String
        octaveSpinner = findViewById<Spinner>(R.id.spinnerOctaveChar42).selectedItem as String
        durationSpinner = findViewById<Spinner>(R.id.spinnerDurationChar42).selectedItem as String

        if(!noteSpinner.isNullOrEmpty() && !octaveSpinner.isNullOrEmpty() && !durationSpinner.isNullOrEmpty())
        {
            var code = GetCode(noteSpinner, octaveSpinner, durationSpinner)

            if(!CheckNotDuplicated(key, code))
            {
                val toast = Toast.makeText(
                    this,
                    "No pueden haber codificaciones repetidas.",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return
            }

            key[41] = code
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se pueden dejar caracteres sin informar.",
                Toast.LENGTH_LONG
            )
            toast.show()
            return
        }
        //endregion

        //endregion

        if(code == 1)
        {
            val intent = Intent(this, EncodeCustom::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
                putExtra(KEY_ARRAY, key)
            }
            startActivity(intent)
        }
        else if(code == 2)
        {
            val intent = Intent(this, DecodeCustom::class.java).apply {
                putExtra(KEY_ARRAY, key)
            }
            startActivity(intent)
        }
    }

    fun CheckNotDuplicated(key: IntArray, code: Int): Boolean
    {
        for(i in key.indices)
        {
            if(key[i] == code)
            {
                return false
            }
        }
        return true
    }

    fun GetCode(note: String, octave: String, duration: String): Int
    {
        var code = 0
        when(duration)
        {
            "1/2"->
            {
                code = CheckHalfDurationOctave(note, octave)
            }
            "1/4"->
            {
                code = CheckQuarterDurationOctave(note, octave)
            }
            "1/8"->
            {
                code = CheckEighthDurationOctave(note, octave)
            }
        }
        return code
    }

    fun CheckHalfDurationOctave(note: String, octave: String): Int
    {
        var code = 0
        when(octave)
        {
            "1"->
            {
                code = CheckHalfDurationFirstOctaveNote(note)
            }
            "2"->
            {
                code = CheckHalfDurationSecondOctaveNote(note)
            }
        }
        return code
    }

    fun CheckQuarterDurationOctave(note: String, octave: String): Int
    {
        var code = 0
        when(octave)
        {
            "1"->
            {
                code = CheckQuarterDurationFirstOctaveNote(note)
            }
            "2"->
            {
                code = CheckQuarterDurationSecondOctaveNote(note)
            }
        }
        return code
    }

    fun CheckEighthDurationOctave(note: String, octave: String): Int
    {
        var code = 0
        when(octave)
        {
            "1"->
            {
                code = CheckEighthDurationFirstOctaveNote(note)
            }
            "2"->
            {
                code = CheckEighthDurationSecondOctaveNote(note)
            }
        }
        return code
    }

    fun CheckHalfDurationFirstOctaveNote(note: String): Int
    {
        var code = 0
        when(note)
        {
            "Do"->
            {
                code = 1
            }
            "Do#"->
            {
                code = 2
            }
            "Re"->
            {
                code = 3
            }
            "Re#"->
            {
                code = 4
            }
            "Mi"->
            {
                code = 5
            }
            "Fa"->
            {
                code = 6
            }
            "Fa#"->
            {
                code = 7
            }
            "Sol"->
            {
                code = 8
            }
            "Sol#"->
            {
                code = 9
            }
            "La"->
            {
                code = 10
            }
            "La#"->
            {
                code = 11
            }
            "Si"->
            {
                code = 12
            }
        }
        return code
    }

    fun CheckHalfDurationSecondOctaveNote(note: String): Int
    {
        var code = 0
        when(note)
        {
            "Do"->
            {
                code = 13
            }
            "Do#"->
            {
                code = 14
            }
            "Re"->
            {
                code = 15
            }
            "Re#"->
            {
                code = 16
            }
            "Mi"->
            {
                code = 17
            }
            "Fa"->
            {
                code = 18
            }
            "Fa#"->
            {
                code = 19
            }
            "Sol"->
            {
                code = 20
            }
            "Sol#"->
            {
                code = 21
            }
            "La"->
            {
                code = 22
            }
            "La#"->
            {
                code = 23
            }
            "Si"->
            {
                code = 24
            }
        }
        return code
    }

    fun CheckQuarterDurationFirstOctaveNote(note: String): Int
    {
        var code = 0
        when(note)
        {
            "Do"->
            {
                code = 25
            }
            "Do#"->
            {
                code = 26
            }
            "Re"->
            {
                code = 27
            }
            "Re#"->
            {
                code = 28
            }
            "Mi"->
            {
                code = 29
            }
            "Fa"->
            {
                code = 30
            }
            "Fa#"->
            {
                code = 31
            }
            "Sol"->
            {
                code = 32
            }
            "Sol#"->
            {
                code = 33
            }
            "La"->
            {
                code = 34
            }
            "La#"->
            {
                code = 35
            }
            "Si"->
            {
                code = 36
            }
        }
        return code
    }

    fun CheckQuarterDurationSecondOctaveNote(note: String): Int
    {
        var code = 0
        when(note)
        {
            "Do"->
            {
                code = 37
            }
            "Do#"->
            {
                code = 38
            }
            "Re"->
            {
                code = 39
            }
            "Re#"->
            {
                code = 40
            }
            "Mi"->
            {
                code = 41
            }
            "Fa"->
            {
                code = 42
            }
            "Fa#"->
            {
                code = 43
            }
            "Sol"->
            {
                code = 44
            }
            "Sol#"->
            {
                code = 45
            }
            "La"->
            {
                code = 46
            }
            "La#"->
            {
                code = 47
            }
            "Si"->
            {
                code = 48
            }
        }
        return code
    }

    fun CheckEighthDurationFirstOctaveNote(note: String): Int
    {
        var code = 0
        when(note)
        {
            "Do"->
            {
                code = 49
            }
            "Do#"->
            {
                code = 50
            }
            "Re"->
            {
                code = 51
            }
            "Re#"->
            {
                code = 52
            }
            "Mi"->
            {
                code = 53
            }
            "Fa"->
            {
                code = 54
            }
            "Fa#"->
            {
                code = 55
            }
            "Sol"->
            {
                code = 56
            }
            "Sol#"->
            {
                code = 57
            }
            "La"->
            {
                code = 58
            }
            "La#"->
            {
                code = 59
            }
            "Si"->
            {
                code = 60
            }
        }
        return code
    }

    fun CheckEighthDurationSecondOctaveNote(note: String): Int
    {
        var code = 0
        when(note)
        {
            "Do"->
            {
                code = 61
            }
            "Do#"->
            {
                code = 62
            }
            "Re"->
            {
                code = 63
            }
            "Re#"->
            {
                code = 64
            }
            "Mi"->
            {
                code = 65
            }
            "Fa"->
            {
                code = 66
            }
            "Fa#"->
            {
                code = 67
            }
            "Sol"->
            {
                code = 68
            }
            "Sol#"->
            {
                code = 69
            }
            "La"->
            {
                code = 70
            }
            "La#"->
            {
                code = 71
            }
            "Si"->
            {
                code = 72
            }
        }
        return code
    }
}