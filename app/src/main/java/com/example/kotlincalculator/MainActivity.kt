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

    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(findViewById<TextView>(R.id.tvInput).text.toString())){
            findViewById<TextView>(R.id.tvInput).append((view as Button).text)
            lastDot = false
            lastNumeric = false
        }
        if ((view as Button).text =="-" && !findViewById<TextView>(R.id.tvInput).text.toString().endsWith("-")){
            findViewById<TextView>(R.id.tvInput).append((view as Button).text)
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

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = findViewById<TextView>(R.id.tvInput).text.toString()
            var prefix =""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    if (prefix == "-"){
                        findViewById<TextView>(R.id.tvInput).text = ((splitValue[0].toDouble()*(-1))-splitValue[1].toDouble()).toString()
                    }
                    else{
                        findViewById<TextView>(R.id.tvInput).text = (splitValue[0].toDouble()-splitValue[1].toDouble()).toString()
                    }
                }
                else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    findViewById<TextView>(R.id.tvInput).text = (splitValue[0].toDouble()+splitValue[1].toDouble()).toString()
                }
                else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    findViewById<TextView>(R.id.tvInput).text = (splitValue[0].toDouble()*splitValue[1].toDouble()).toString()
                }
                else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    findViewById<TextView>(R.id.tvInput).text = (splitValue[0].toDouble()/splitValue[1].toDouble()).toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}