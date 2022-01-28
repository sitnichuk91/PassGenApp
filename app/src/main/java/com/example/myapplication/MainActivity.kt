package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.R.attr.label
import android.content.ClipboardManager
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.ActionBar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var dictionaryButton: Button = findViewById(R.id.dictionary)

        var passResult: TextView = findViewById(R.id.passResult)
        var passGen: Button = findViewById(R.id.passGen)

        var checkCapital: CheckBox = findViewById(R.id.capitalLetters)
        var checkSmall: CheckBox = findViewById(R.id.smallLetters)
        var checkNumbers: CheckBox = findViewById(R.id.numbers)
        var checkSymbol: CheckBox = findViewById(R.id.symbols)

        var passLength: SeekBar = findViewById(R.id.passLength)

        dictionaryButton.setOnClickListener {
            val myIntent = Intent (this, Dictionary::class.java)
            startActivity(myIntent)
        }


        var capitalList = mutableListOf<String>("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        var smallList = mutableListOf<String>("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
        var numberList = mutableListOf<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        var symbolList = mutableListOf<String>("@", "!", "#", "$", "%", "*")

        var passwordLength = 20


        passGen.setOnClickListener {
            var passList = mutableListOf<String>()
            if (checkSmall.isChecked || checkCapital.isChecked || checkNumbers.isChecked || checkSymbol.isChecked){
                if (checkCapital.isChecked) {
                    for (i in 0..25){
                        passList.add(capitalList[i])
                    }
                }
                if (checkSmall.isChecked){
                    for (i in 0..25){
                        passList.add(smallList[i])
                    }
                }
                if (checkNumbers.isChecked){
                    for (i in 0..9){
                        passList.add(numberList[i])
                    }
                }
                if (checkSymbol.isChecked){
                    for (i in 0..5){
                        passList.add(symbolList[i])
                    }
                }
                var userPass = mutableListOf<String>()

                for (i in 1..passwordLength){
                    userPass.add(passList.random())
                }
                passResult.text = userPass.joinToString(separator = "")
                Toast.makeText(this@MainActivity, "Коснитесь пароля, чтобы скопировать.", Toast.LENGTH_SHORT).show()
            } else passResult.text = "Пожалуйста, выберите состав пароля!"

        }

        passLength.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    passwordLength = seekBar.progress
                    Toast.makeText(this@MainActivity, "${seekBar.progress}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        passResult.setOnClickListener {
            val clipboard: ClipboardManager =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                } else {
                    TODO("VERSION.SDK_INT < M")
                }
            val clip = ClipData.newPlainText(label.toString(), passResult.text.toString())
            clipboard.setPrimaryClip(clip)

        }


    }
}