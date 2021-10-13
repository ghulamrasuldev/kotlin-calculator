package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

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

    fun isOperator(view: View){
        if (lastNumeric && isOperatorAdded(findViewById<TextView>(R.id.tvInput).text.toString())){
            findViewById<TextView>(R.id.tvInput).append((view as Button).text)
            lastDot = false
            lastNumeric = false
        }
    }
    private fun isOperatorAdded (value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        }
        else {
            value.contains("+") || value.contains("/") || value.contains("+")
        }
    }
}