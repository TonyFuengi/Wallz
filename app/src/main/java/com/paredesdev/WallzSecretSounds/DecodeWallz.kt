package com.paredesdev.WallzSecretSounds

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pdrogfer.mididroid.MidiFile
import com.pdrogfer.mididroid.event.MidiEvent
import com.pdrogfer.mididroid.event.NoteOn
import java.io.File
import java.io.IOException

class DecodeWallz : AppCompatActivity() {
    var wallzDiscLetters = arrayOf('E', 'A', 'O', ' ', 'S', 'R', ';', 'N', '-', 'I', 'D', '.', 'L', 'C', 'T', ',', 'U', 'M', '(', 'P', 'B', '¿', 'G', ')', 'V', 'Y', '?', 'Q', 'H', 'F', '¡', 'Z', '_', 'J', '!', 'Ñ', ':', 'X', '"', 'K', 'W', '+')
    var wallzDiscNumbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42)

    private val wallzSize = wallzDiscLetters.size
    private val wallzMaxPos = wallzSize -1
    private var decodeMessage = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.decode_wallz_activity)
    }

    @SuppressLint("SetTextI18n")
    fun decodeMidiFileWallz(view: View)
    {
        val intvlString = findViewById<EditText>(R.id.editTextNumberW).text.toString()
        val jumpString = findViewById<EditText>(R.id.editTextNumberW2).text.toString()

        if(!"".equals(intvlString) && Integer.parseInt(intvlString) >= 0 && Integer.parseInt(intvlString) <= 100 && !"".equals(jumpString) && Integer.parseInt(jumpString) >= 0 && Integer.parseInt(jumpString) <= 26)
        {
            var intvlInt = Integer.parseInt(intvlString)
            var jumplInt = Integer.parseInt(jumpString)

            try
            {
                val inputFile = File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "codigoWallz.mid")
                val midiFile = MidiFile(inputFile)
                val track = midiFile.tracks[1]
                val iteration: Iterator<MidiEvent> = track.events.iterator()

                var doneInt = 0
                var previousTick: Long
                var currentTick: Long
                var event: MidiEvent = iteration.next()

                val buttonFile = findViewById<Button>(R.id.buttonW10)
                buttonFile.isEnabled = false

                while (iteration.hasNext())
                {
                    if(event::class == NoteOn::class)
                    {
                        var note: NoteOn = event as NoteOn
                        var noteValue = note.noteValue
                        previousTick = note.tick

                        event = iteration.next()
                        currentTick = (event as NoteOn).tick

                        val durationTicks = currentTick - previousTick
                        previousTick = currentTick

                        if(durationTicks == 960L) //Blanca
                        {
                            decodeMinimNote(noteValue)
                            doneInt++
                        }
                        if(durationTicks == 480L) //Negra
                        {
                            decodeCrotchetNote(noteValue)
                            doneInt++
                        }
                        if(durationTicks == 240L) //Corchea
                        {
                            decodeQuaverNotes(noteValue)
                            doneInt++
                        }

                        if(intvlInt != 0 && jumplInt != 0) {
                            if (doneInt == intvlInt)
                            {
                                spinWallzDisc(jumplInt)
                                doneInt = 0
                            }
                        }
                    }
                }

                findViewById<TextView>(R.id.textViewW3).apply {
                    text = decodeMessage + System.getProperty ("line.separator")
                }
            }
            catch(e: IOException)
            {
                System.err.println("Error parsing MIDI file:")
                e.printStackTrace();
            }
        }
        else
        {
            val toast = Toast.makeText(this, "El valor del Intervalo debe estar entre 0 y 100 y el del Salto debe estar entre 0 y 26, ambos inclusive.", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun spinWallzDisc(jumplInt: Int)
    {
        var result = 0

        for(i in 0..wallzMaxPos)
        {
            if(wallzDiscNumbers[i] == 1)
            {
                result = i
                break
            }
        }

        result += jumplInt

        if(result >= wallzSize)
        {
            result %= wallzSize
        }

        for(i in 1..wallzSize)
        {
            wallzDiscNumbers[result] = i
            result++

            if(result == wallzSize)
            {
                result %= wallzSize
            }
        }
    }

    //Decodifica notas Blancas
    fun decodeMinimNote(noteValue:Int)
    {
        var characterCode = -1

        when (noteValue)
        {
            60 ->
            {
                characterCode = 1
            }
            62 ->
            {
                characterCode = 2
            }
            64 ->
            {
                characterCode = 3
            }
            65 ->
            {
                characterCode = 4
            }
            67 ->
            {
                characterCode = 5
            }
            69 ->
            {
                characterCode = 6
            }
            71 ->
            {
                characterCode = 7
            }
            72 ->
            {
                characterCode = 22
            }
            74 ->
            {
                characterCode = 23
            }
            76 ->
            {
                characterCode = 24
            }
            77 ->
            {
                characterCode = 25
            }
            79 ->
            {
                characterCode = 26
            }
            81 ->
            {
                characterCode = 27
            }
            83 ->
            {
                characterCode = 28
            }
        }

        val character = findLetter(characterCode)

        if(character != "&")
        {
            decodeMessage += character
        }
    }

    //Decodifica notas Negras
    fun decodeCrotchetNote(noteValue:Int)
    {
        var characterCode = -1

        when (noteValue)
        {
            60 ->
            {
                characterCode = 8
            }
            62 ->
            {
                characterCode = 9
            }
            64 ->
            {
                characterCode = 10
            }
            65 ->
            {
                characterCode = 11
            }
            67 ->
            {
                characterCode = 12
            }
            69 ->
            {
                characterCode = 13
            }
            71 ->
            {
                characterCode = 14
            }
            72 ->
            {
                characterCode = 29
            }
            74 ->
            {
                characterCode = 30
            }
            76 ->
            {
                characterCode = 31
            }
            77 ->
            {
                characterCode = 32
            }
            79 ->
            {
                characterCode = 33
            }
            81 ->
            {
                characterCode = 34
            }
            83 ->
            {
                characterCode = 35
            }
        }

        val character = findLetter(characterCode)

        if(character != "&")
        {
            decodeMessage += character
        }
    }

    //Decodifica notas Corcheas
    fun decodeQuaverNotes(noteValue:Int)
    {
        var characterCode = -1

        when (noteValue)
        {
            60 ->
            {
                characterCode = 15
            }
            62 ->
            {
                characterCode = 16
            }
            64 ->
            {
                characterCode = 17
            }
            65 ->
            {
                characterCode = 18
            }
            67 ->
            {
                characterCode = 19
            }
            69 ->
            {
                characterCode = 20
            }
            71 ->
            {
                characterCode = 21
            }
            72 ->
            {
                characterCode = 36
            }
            74 ->
            {
                characterCode = 37
            }
            76 ->
            {
                characterCode = 38
            }
            77 ->
            {
                characterCode = 39
            }
            79 ->
            {
                characterCode = 40
            }
            81 ->
            {
                characterCode = 41
            }
            83 ->
            {
                characterCode = 42
            }
        }

        val character = findLetter(characterCode)

        if(character != "&")
        {
            decodeMessage += character
        }
    }

    fun findLetter(letterCode: Int):String
    {
        var result = -1

        for(i in 0..wallzMaxPos)
        {
            if(wallzDiscNumbers[i] == letterCode)
            {
                result = i
                break
            }
        }
        if(result >= 0)
        {
            val character = wallzDiscLetters[result]
            return character.toString()
        }
        else
        {
            return "&"
        }
    }
}