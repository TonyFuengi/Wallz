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


class EncodeCustom : AppCompatActivity()
{
    var customDiscLetters = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '_', '-', ',', ';', ':', '!', '¡', '?', '¿', '.', '"', '(', ')', '+')
    var customDiscNumbers = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    private val customSize = customDiscLetters.size
    private val customMaxPos = customSize -1
    private var count = 0
    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.encode_custom_activity)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.textViewCustom3).apply {
            text = message
        }
        customDiscNumbers = intent.getIntArrayExtra(KEY_ARRAY) as IntArray
    }

    fun createMidiFileCustom(view: View)
    {
        val intvlString = findViewById<EditText>(R.id.editTextNumberCustom).text.toString()
        val jumpString = findViewById<EditText>(R.id.editTextNumberCustom2).text.toString()

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
                var intvlInt = Integer.parseInt(intvlString)
                var jumplInt = Integer.parseInt(jumpString)
                val message = findViewById<TextView>(R.id.textViewCustom3).text.toString()

                val buttonFile = findViewById<Button>(R.id.buttonCustom4)
                buttonFile.isEnabled = false

                encodeCustom(intvlInt, jumplInt, message)

                val buttonPlay = findViewById<Button>(R.id.buttonCustom5)
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

    fun encodeCustom (intvlInt: Int, jumplInt: Int, message: String)
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
            var encodeNumer = searchCharactersInCustomDisc(messageArray[i])

            if(encodeNumer > 0)
            {
                noteTrack = addMidiNote(encodeNumer, noteTrack) //Añadimos al noteTrack la nota o notas que correspondan al carácter encontrado

                if(intvlInt != 0 && jumplInt != 0) {
                    doneInt++
                    if (doneInt == intvlInt)
                    {
                        spinCustomDisc(jumplInt)
                        doneInt = 0
                    }
                }
            }
        }
        noteTrack = addMidiNote(73, noteTrack) //Añadimos al noteTrack la nota que marca el final del fichero (Carácter & MIDI 36)

        val tracks: MutableList<MidiTrack> = ArrayList()
        tracks.add(tempoTrack)
        tracks.add(noteTrack)
        val midi = MidiFile(MidiFile.DEFAULT_RESOLUTION, tracks as java.util.ArrayList<MidiTrack>?)
        val output = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MUSIC
            ), "codigoCustom.mid"
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

    fun searchCharactersInCustomDisc (char: Char): Int
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

        for(i in 0..customMaxPos)
        {
            if(customDiscLetters[i] == charToEncode)
            {
                result = customDiscNumbers[i]
                break
            }
        }
        return result
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

    fun addMidiNote(encodeNumber: Int, noteTrack: MidiTrack): MidiTrack
    {
        val channel = 0
        val velocity = 100
        var pitch: Int
        var tick: Long

        when (encodeNumber)
        {
            1 ->
            {
                pitch = 60;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            2 ->
            {
                pitch = 61;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            3 ->
            {
                pitch = 62;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            4 ->
            {
                pitch = 63;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            5 ->
            {
                pitch = 64;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            6 ->
            {
                pitch = 65;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            7 ->
            {
                pitch = 66;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            8 ->
            {
                pitch = 67;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            9 ->
            {
                pitch = 68;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            10 ->
            {
                pitch = 69;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            11 ->
            {
                pitch = 70;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            12 ->
            {
                pitch = 71;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            13 ->
            {
                pitch = 72;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            14 ->
            {
                pitch = 73;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            15 ->
            {
                pitch = 74;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            16 ->
            {
                pitch = 75;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            17 ->
            {
                pitch = 76;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            18 ->
            {
                pitch = 77;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            19 ->
            {
                pitch = 78;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            20 ->
            {
                pitch = 79;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            21 ->
            {
                pitch = 80;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            22 ->
            {
                pitch = 81;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            23 ->
            {
                pitch = 82;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            24 ->
            {
                pitch = 83;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 960);
                count += 4;
            }
            25 ->
            {
                pitch = 60;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            26 ->
            {
                pitch = 61;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            27 ->
            {
                pitch = 62;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            28 ->
            {
                pitch = 63;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            29 ->
            {
                pitch = 64;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            30 ->
            {
                pitch = 65;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            31 ->
            {
                pitch = 66;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            32 ->
            {
                pitch = 67;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            33 ->
            {
                pitch = 68;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            34 ->
            {
                pitch = 69;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            35 ->
            {
                pitch = 70;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            36 ->
            {
                pitch = 71;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            37 ->
            {
                pitch = 72;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            38 ->
            {
                pitch = 73;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            39 ->
            {
                pitch = 74;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            40 ->
            {
                pitch = 75;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            41 ->
            {
                pitch = 76;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            42 ->
            {
                pitch = 77;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            43 ->
            {
                pitch = 78;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            44 ->
            {
                pitch = 79;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            45 ->
            {
                pitch = 80;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            46 ->
            {
                pitch = 81;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            47 ->
            {
                pitch = 82;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            48 ->
            {
                pitch = 83;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 480);
                count += 2;
            }
            49 ->
            {
                pitch = 60;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            50 ->
            {
                pitch = 61;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            51 ->
            {
                pitch = 62;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            52 ->
            {
                pitch = 63;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            53 ->
            {
                pitch = 64;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            54 ->
            {
                pitch = 65;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            55 ->
            {
                pitch = 66;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            56 ->
            {
                pitch = 67;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            57 ->
            {
                pitch = 68;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            58 ->
            {
                pitch = 69;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            59 ->
            {
                pitch = 70;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            60 ->
            {
                pitch = 71;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            61 ->
            {
                pitch = 72;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            62 ->
            {
                pitch = 73;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            63 ->
            {
                pitch = 74;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            64 ->
            {
                pitch = 75;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            65 ->
            {
                pitch = 76;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            66 ->
            {
                pitch = 77;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            67 ->
            {
                pitch = 78;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            68 ->
            {
                pitch = 79;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            69 ->
            {
                pitch = 80;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            70 ->
            {
                pitch = 81;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            71 ->
            {
                pitch = 82;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            72 ->
            {
                pitch = 83;
                tick = count * 240L;
                noteTrack.insertNote(channel, pitch, velocity, tick, 240);
                count++;
            }
            73 ->
            {
                pitch = 36
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
                ).path + File.separator + "codigoCustom.mid")
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
                "Ha habido un error al reproducir el archivo codigoCustom.mid",
                Toast.LENGTH_LONG
            )
            toast.show()
            e.printStackTrace()
        }
    }
}