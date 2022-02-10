package com.paredesdev.WallzSecretSounds

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pdrogfer.mididroid.MidiFile
import com.pdrogfer.mididroid.MidiTrack
import com.pdrogfer.mididroid.event.meta.Tempo
import com.pdrogfer.mididroid.event.meta.TimeSignature
import java.io.File
import java.io.IOException

class EncodeGuyot : AppCompatActivity() {
    val guyotDiscLetters = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z')
    val guyotDiscNumbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26)

    private val guyotSize = guyotDiscLetters.size
    private val guyotMaxPos = guyotSize -1
    private var count = 0
    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.encode_guyot_activity)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val textView = findViewById<TextView>(R.id.textView3).apply {
            text = message
        }
    }

    fun createMidiFileGuyot(view: View)
    {
        val intvlString = findViewById<EditText>(R.id.editTextNumber).text.toString()
        val jumpString = findViewById<EditText>(R.id.editTextNumber2).text.toString()
        var intvlInt: Int = 0
        var jumplInt: Int = 0

        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if(permissionCheck == android.content.pm.PackageManager.PERMISSION_GRANTED)
        {
            if (!"".equals(intvlString) && Integer.parseInt(intvlString) >= 0 && Integer.parseInt(
                    intvlString
                ) <= 100 && !"".equals(jumpString) && Integer.parseInt(jumpString) >= 0 && Integer.parseInt(
                    jumpString
                ) <= 26
            ) {
                intvlInt = Integer.parseInt(intvlString)
                jumplInt = Integer.parseInt(jumpString)
                val message = findViewById<TextView>(R.id.textView3).text.toString()

                val buttonFile = findViewById<Button>(R.id.button4)
                buttonFile.isEnabled = false

                encodeGuyot(intvlInt, jumplInt, message)

                val buttonPlay = findViewById<Button>(R.id.button5)
                buttonPlay.isEnabled = true
            }
            else
            {
                val toast = Toast.makeText(
                    this,
                    "El valor del Intervalo debe estar entre 0 y 100 y el del Salto debe estar entre 0 y 26, ambos incluidos.",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
        }
        else
        {
            val toast = Toast.makeText(
                this,
                "No se puede codificar y crear el MIDI porque no se tiene permiso para crear el archivo.",
                Toast.LENGTH_LONG
            )
            toast.show()
        }
    }

    fun encodeGuyot (intvlInt: Int, jumplInt: Int, message: String)
    {
        val messageArray = message.toUpperCase().toCharArray()
        var doneInt = 0

        val tempoTrack = MidiTrack()
        var noteTrack = MidiTrack()

        val ts = TimeSignature()
        ts.setTimeSignature(
            4, 4, TimeSignature.DEFAULT_METER,
            TimeSignature.DEFAULT_DIVISION
        )
        val tempo = Tempo()
        tempo.bpm = 45f
        tempoTrack.insertEvent(ts)
        tempoTrack.insertEvent(tempo)

        for(i in 0..(messageArray.size - 1))
        {
            var encodeNumer = searchCharactersInGuyotDisc(messageArray[i])

            if(encodeNumer > 0)
            {
                noteTrack = addMidiNote(encodeNumer, noteTrack) //Añadimos al noteTrack la nota o notas que correspondan al carácter encontrado

                if(intvlInt != 0 && jumplInt != 0) {
                    doneInt++
                    if (doneInt == intvlInt)
                    {
                        spinGuyotDisc(jumplInt)
                        doneInt = 0
                    }
                }
            }
        }
        noteTrack = addMidiNote(27, noteTrack) //Añadimos al noteTrack la nota que marca el final del fichero (Carácter & MIDI 47)

        val tracks: MutableList<MidiTrack> = ArrayList()
        tracks.add(tempoTrack)
        tracks.add(noteTrack)
        val midi = MidiFile(MidiFile.DEFAULT_RESOLUTION, tracks as java.util.ArrayList<MidiTrack>?)
        val output = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MUSIC
            ), "codigoGuyot.mid"
        )
        try {
            midi.writeToFile(output)

            val toast = Toast.makeText(
                this,
                "Se ha generado el archivo.",
                Toast.LENGTH_LONG
            )
            toast.show()
        } catch (e: IOException) {
            System.err.println(e)
        }
    }

    fun searchCharactersInGuyotDisc (char: Char): Int
    {
        var result = 0
        var charToEncode = char

        when(charToEncode)
        {
            'Á' -> { charToEncode = 'A'}
            'É' -> { charToEncode = 'E'}
            'Í' -> { charToEncode = 'I'}
            'Ó' -> { charToEncode = 'O'}
            'Ú' -> { charToEncode = 'U'}
        }

        for(i in 0..guyotMaxPos)
        {
            if(guyotDiscLetters[i] == charToEncode)
            {
                result = guyotDiscNumbers[i]
                break
            }
        }
        return result
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

    fun addMidiNote(encodeNumber: Int, noteTrack: MidiTrack): MidiTrack
    {
        val channel = 0
        val velocity = 100
        var pitch = 0
        var tick = 0L
        var duration = 0L

        when (encodeNumber)
        {
            1 ->
            {
                pitch = 60
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 69
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            2 ->
            {
                pitch = 65
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 60
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            3 ->
            {
                pitch = 64
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 64
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            4 ->
            {
                pitch = 64
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
                count = count+2
            }
            5 ->
            {
                pitch = 79
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 77
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            6 ->
            {
                pitch = 76
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 64
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            7 ->
            {
                pitch = 69
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
                count = count+2
            }
            8 ->
            {
                pitch = 65
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 65
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            9 ->
            {
                pitch = 67
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 960)
                count = count+4
            }
            10, 22 ->
            {
                pitch = 72
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
                count = count+2
            }
            11 ->
            {
                pitch = 64
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 62
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            12 ->
            {
                pitch = 72
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 71
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            13 ->
            {
                pitch = 71
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 71
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            14 ->
            {
                pitch = 65
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
                count = count+2
            }
            15 ->
            {
                pitch = 76
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 77
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            16 ->
            {
                pitch = 71
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 69
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            17 ->
            {
                pitch = 67
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
                count = count+2
            }
            18 ->
            {
                pitch = 72
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 74
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            19, 26 ->
            {
                pitch = 77
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
                count = count+2
            }
            20 ->
            {
                pitch = 69
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 67
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            21 ->
            {
                pitch = 77
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
                pitch = 79
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 240)
                count++
            }
            23 ->
            {
                pitch = 69
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 960)
                count = count+4
            }
            24 ->
            {
                pitch = 74
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
                count = count+2
            }
            25 ->
            {
                pitch = 76
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
                count = count+2
            }
            27 ->
            {
                pitch = 47
                tick = count * 240L
                noteTrack.insertNote(channel, pitch, velocity, tick, 480)
            }
        }

        return noteTrack
    }

    fun audioPlayer(view: View)
    {
        try {
            if(!mediaPlayer.isPlaying)
            {
                mediaPlayer.reset()
                val myUri = Uri.parse(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MUSIC
                ).path + File.separator + "codigoGuyot.mid")
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaPlayer.setDataSource(applicationContext, myUri)

                mediaPlayer.prepare()
                mediaPlayer.start()
            }
            else {
                mediaPlayer.stop()
            }
        } catch (e: Exception) {
            val toast = Toast.makeText(
                this,
                "Ha habido un error al reproducir el archivo codigoGuyot.mid",
                Toast.LENGTH_LONG
            )
            toast.show()
            e.printStackTrace()
        }
    }
}