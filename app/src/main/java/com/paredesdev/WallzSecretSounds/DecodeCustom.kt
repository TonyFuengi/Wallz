package com.paredesdev.WallzSecretSounds

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

class DecodeCustom : AppCompatActivity()
{
    var customDiscLetters = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '_', '-', ',', ';', ':', '!', '¡', '?', '¿', '.', '"', '(', ')', '+')
    var customDiscNumbers = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    private val customSize = customDiscLetters.size
    private val customMaxPos = customSize -1
    private var decodeMessage = ""


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.decode_custom_activity)

        customDiscNumbers = intent.getIntArrayExtra(KEY_ARRAY) as IntArray
    }

    fun decodeMidiFileCustom(view: View)
    {
        val intvlString = findViewById<EditText>(R.id.editTextNumberCustom).text.toString()
        val jumpString = findViewById<EditText>(R.id.editTextNumberCustom2).text.toString()

        if(!"".equals(intvlString) && Integer.parseInt(intvlString) >= 0 && Integer.parseInt(intvlString) <= 100 && !"".equals(jumpString) && Integer.parseInt(jumpString) >= 0 && Integer.parseInt(jumpString) <= 26)
        {
            var intvlInt = Integer.parseInt(intvlString)
            var jumplInt = Integer.parseInt(jumpString)

            try
            {
                val inputFile = File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "codigoCustom.mid")
                val midiFile = MidiFile(inputFile)
                val track = midiFile.tracks[1]
                val iteration: Iterator<MidiEvent> = track.events.iterator()

                var doneInt = 0
                var previousTick: Long
                var currentTick: Long
                var event: MidiEvent = iteration.next()

                val buttonFile = findViewById<Button>(R.id.buttonCustom10)
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
                            decodeMinimNoteCustom(noteValue)
                            doneInt++
                        }
                        if(durationTicks == 480L) //Negra
                        {
                            decodeCrotchetNoteCustom(noteValue)
                            doneInt++
                        }
                        if(durationTicks == 240L) //Corchea
                        {
                            decodeQuaverNotesCustom(noteValue)
                            doneInt++
                        }

                        if(intvlInt != 0 && jumplInt != 0) {
                            if (doneInt == intvlInt)
                            {
                                spinCustomDisc(jumplInt)
                                doneInt = 0
                            }
                        }
                    }
                }

                findViewById<TextView>(R.id.textViewCustom3).apply {
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

    fun spinCustomDisc(jumplInt: Int)
    {
        var result = jumplInt
        var newKey = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        for(i in customDiscNumbers.indices)
        {
            if(result >= customSize)
            {
                result %= customSize
            }

            newKey[result] = customDiscNumbers[i]

            result ++
        }

        customDiscNumbers = newKey
    }

    //Decodifica notas Blancas
    fun decodeMinimNoteCustom(noteValue:Int)
    {
        var characterCode = -1

        when (noteValue)
        {
            60 ->
            {
                characterCode = 1
            }
            61 ->
            {
                characterCode = 2
            }
            62 ->
            {
                characterCode = 3
            }
            63 ->
            {
                characterCode = 4
            }
            64 ->
            {
                characterCode = 5
            }
            65 ->
            {
                characterCode = 6
            }
            66 ->
            {
                characterCode = 7
            }
            67 ->
            {
                characterCode = 8
            }
            68 ->
            {
                characterCode = 9
            }
            69 ->
            {
                characterCode = 10
            }
            70 ->
            {
                characterCode = 11
            }
            71 ->
            {
                characterCode = 12
            }
            72 ->
            {
                characterCode = 13
            }
            73 ->
            {
                characterCode = 14
            }
            74 ->
            {
                characterCode = 15
            }
            75 ->
            {
                characterCode = 16
            }
            76 ->
            {
                characterCode = 17
            }
            77 ->
            {
                characterCode = 18
            }
            78 ->
            {
                characterCode = 19
            }
            79 ->
            {
                characterCode = 20
            }
            80 ->
            {
                characterCode = 21
            }
            81 ->
            {
                characterCode = 22
            }
            82 ->
            {
                characterCode = 23
            }
            83 ->
            {
                characterCode = 24
            }
        }

        val character = findLetter(characterCode)

        if(character != "&")
        {
            decodeMessage += character
        }
    }

    //Decodifica notas Negras
    fun decodeCrotchetNoteCustom(noteValue:Int)
    {
        var characterCode = -1

        when (noteValue)
        {
            60 ->
            {
                characterCode = 25
            }
            61 ->
            {
                characterCode = 26
            }
            62 ->
            {
                characterCode = 27
            }
            63 ->
            {
                characterCode = 28
            }
            64 ->
            {
                characterCode = 29
            }
            65 ->
            {
                characterCode = 30
            }
            66 ->
            {
                characterCode = 31
            }
            67 ->
            {
                characterCode = 32
            }
            68 ->
            {
                characterCode = 33
            }
            69 ->
            {
                characterCode = 34
            }
            70 ->
            {
                characterCode = 35
            }
            71 ->
            {
                characterCode = 36
            }
            72 ->
            {
                characterCode = 37
            }
            73 ->
            {
                characterCode = 38
            }
            74 ->
            {
                characterCode = 39
            }
            75 ->
            {
                characterCode = 40
            }
            76 ->
            {
                characterCode = 41
            }
            77 ->
            {
                characterCode = 42
            }
            78 ->
            {
                characterCode = 43
            }
            79 ->
            {
                characterCode = 44
            }
            80 ->
            {
                characterCode = 45
            }
            81 ->
            {
                characterCode = 46
            }
            82 ->
            {
                characterCode = 47
            }
            83 ->
            {
                characterCode = 48
            }
        }

        val character = findLetter(characterCode)

        if(character != "&")
        {
            decodeMessage += character
        }
    }

    //Decodifica notas Corcheas
    fun decodeQuaverNotesCustom(noteValue:Int)
    {
        var characterCode = -1

        when (noteValue)
        {
            60 ->
            {
                characterCode = 49
            }
            61 ->
            {
                characterCode = 50
            }
            62 ->
            {
                characterCode = 51
            }
            63 ->
            {
                characterCode = 52
            }
            64 ->
            {
                characterCode = 53
            }
            65 ->
            {
                characterCode = 54
            }
            66 ->
            {
                characterCode = 55
            }
            67 ->
            {
                characterCode = 56
            }
            68 ->
            {
                characterCode = 57
            }
            69 ->
            {
                characterCode = 58
            }
            70 ->
            {
                characterCode = 59
            }
            71 ->
            {
                characterCode = 60
            }
            72 ->
            {
                characterCode = 61
            }
            73 ->
            {
                characterCode = 62
            }
            74 ->
            {
                characterCode = 63
            }
            75 ->
            {
                characterCode = 64
            }
            76 ->
            {
                characterCode = 65
            }
            77 ->
            {
                characterCode = 66
            }
            78 ->
            {
                characterCode = 67
            }
            79 ->
            {
                characterCode = 68
            }
            80 ->
            {
                characterCode = 69
            }
            81 ->
            {
                characterCode = 70
            }
            82 ->
            {
                characterCode = 71
            }
            83 ->
            {
                characterCode = 72
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

        for(i in customDiscLetters.indices)
        {
            if(customDiscNumbers[i] == letterCode)
            {
                result = i
                break
            }
        }
        if(result >= 0)
        {
            val character = customDiscLetters[result]
            return character.toString()
        }
        else
        {
            return "&"
        }
    }
}