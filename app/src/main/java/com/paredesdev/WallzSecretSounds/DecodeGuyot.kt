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

class DecodeGuyot : AppCompatActivity() {
    val guyotDiscLetters = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z')
    val guyotDiscNumbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26)

    private val guyotSize = guyotDiscLetters.size
    private val guyotMaxPos = guyotSize -1
    private var decodeMessage = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.decode_guyot_activity)
    }

    fun decodeMidiFileGuyot(view: View)
    {
        val intvlString = findViewById<EditText>(R.id.editTextNumber).text.toString()
        val jumpString = findViewById<EditText>(R.id.editTextNumber2).text.toString()
        var intvlInt: Int = 0
        var jumplInt: Int = 0

        if(!"".equals(intvlString) && Integer.parseInt(intvlString) >= 0 && Integer.parseInt(intvlString) <= 100 && !"".equals(jumpString) && Integer.parseInt(jumpString) >= 0 && Integer.parseInt(jumpString) <= 26)
        {
            intvlInt = Integer.parseInt(intvlString)
            jumplInt = Integer.parseInt(jumpString)

            try
            {
                val inputFile = File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "codigoGuyot.mid")
                val midiFile = MidiFile(inputFile)
                val track = midiFile.tracks[1]
                val iteration: Iterator<MidiEvent> = track.events.iterator()

                var doneInt = 0
                var previousTick = 0L
                var currentTick = 0L
                var event: MidiEvent = iteration.next()
                var firstQuaverNote: Int = 0
                var firstQuaverBool = false

                val buttonFile = findViewById<Button>(R.id.button10)
                buttonFile.isEnabled = false

                while (iteration.hasNext())
                {
                    if(event::class == NoteOn::class)
                    {
                        var note: NoteOn = event as NoteOn
                        var noteValue = note.noteValue
                        previousTick = note.tick

                        event = iteration.next() // Leemos la siguiente nota
                        currentTick = (event as NoteOn).tick

                        val durationTicks = currentTick - previousTick // Obtenemos duración de la nota anterior
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
                            if(!firstQuaverBool)
                            {
                                firstQuaverNote = noteValue
                                firstQuaverBool = true
                            }
                            else
                            {
                                decodeQuaverNotes(firstQuaverNote, noteValue)
                                firstQuaverBool = false
                                doneInt++
                            }
                        }

                        if(intvlInt != 0 && jumplInt != 0) {
                            if (doneInt == intvlInt)
                            {
                                spinGuyotDisc(jumplInt)
                                doneInt = 0
                            }
                        }
                    }
                }

                val textView = findViewById<TextView>(R.id.textView3).apply {
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

    fun spinGuyotDisc(jumplInt: Int)
    {
        var result = 0

        for(i in 0..guyotMaxPos)
        {
            if(guyotDiscNumbers[i] == 1)
            {
                result = i
                break
            }
        }

        result += jumplInt

        if(result >= guyotSize)
        {
            result %= guyotSize
        }

        for(i in 1..guyotSize)
        {
            guyotDiscNumbers[result] = i
            result++

            if(result == guyotSize)
            {
                result %= guyotSize
            }
        }
    }

    //Decodifica notas Blancas
    fun decodeMinimNote(noteValue:Int)
    {
        var characterCode = -1

        when (noteValue)
        {
            67 ->
            {
                characterCode = 9
            }
            69 ->
            {
                characterCode = 23
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
            64 ->
            {
                characterCode = 4
            }
            69 ->
            {
                characterCode = 7
            }
            72 ->
            {
                characterCode = 10
            }
            65 ->
            {
                characterCode = 14
            }
            67 ->
            {
                characterCode = 17
            }
            77 ->
            {
                characterCode = 19
            }
            74 ->
            {
                characterCode = 24
            }
            76 ->
            {
                characterCode = 25
            }
        }
        val character = findLetter(characterCode)

        if(character != "&")
        {
            decodeMessage += character
        }
    }

    //Decodifica notas Corcheas
    fun decodeQuaverNotes(noteValue:Int, noteValue2:Int)
    {
        var characterCode = -1

        when (noteValue)
        {
            60 -> {
                if (noteValue2 == 69) {
                    characterCode = 1
                }
            }
            72 -> {
                if (noteValue2 == 71) {
                    characterCode = 12
                }
                if (noteValue2 == 74) {
                    characterCode = 18
                }
            }
            65 -> {
                if (noteValue2 == 60) {
                    characterCode = 2
                }
                if (noteValue2 == 65) {
                    characterCode = 8
                }
            }
            77 -> {
                if (noteValue2 == 79) {
                    characterCode = 21
                }
            }
            69 -> {
                if (noteValue2 == 67) {
                    characterCode = 20
                }
            }
            64 -> {
                if (noteValue2 == 64) {
                    characterCode = 3
                }
                if (noteValue2 == 62) {
                    characterCode = 11
                }
            }
            76 -> {
                if (noteValue2 == 64) {
                    characterCode = 6
                }
                if (noteValue2 == 77) {
                    characterCode = 15
                }
            }
            71 -> {
                if (noteValue2 == 71) {
                    characterCode = 13
                }
                if (noteValue2 == 69) {
                    characterCode = 16
                }
            }
            79 -> {
                if (noteValue2 == 77) {
                    characterCode = 5
                }
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

        for(i in 0..guyotMaxPos)
        {
            if(guyotDiscNumbers[i] == letterCode)
            {
                result = i
                break
            }
        }
        if(result >= 0)
        {
            val character = guyotDiscLetters[result]
            return character.toString()
        }
        else
        {
            return "&"
        }
    }
}