package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        findViewById<TextView>(R.id.tvInput).append((view as Button).text)
        lastNumeric = true
    }

    fun onCLR(view: View){
        findViewById<TextView>(R.id.tvInput).text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onPoint(view: View){
        if(lastNumeric && !lastDot){
            findViewById<TextView>(R.id.tvInput).append((view as Button).text)
            lastNumeric = false
            lastDot = true
        }
    }
}