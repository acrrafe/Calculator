package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView



// THIS IS A CALCULATOR APPLICATION WITH BASIC FUNCTIONALITIES

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    // Numbers
    private var numberZero: Button? = null
    private var numOne: Button? = null
    private var numTwo: Button? = null
    private var numThree: Button? = null
    private var numFour: Button? = null
    private var numFive: Button? = null
    private var numSix: Button? = null
    private var numSeven: Button? = null
    private var numEight: Button? = null
    private var numNine: Button? = null
    // Operators
    private var divideOper: Button? = null
    private var multiplyOper: Button? = null
    private var minusOper: Button? = null
    private var addOper: Button? = null
    private var clrBtn: Button? = null
    private var equalOper: Button? = null
    private var decimalOper: Button? = null
    // Flags
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TextView Input
        tvInput = findViewById(R.id.tv_Input)
        // Numbers
        numberZero = findViewById(R.id.numZero)
        numOne = findViewById(R.id.numOne)
        numTwo = findViewById(R.id.numTwo)
        numThree = findViewById(R.id.numThree)
        numFour = findViewById(R.id.numFour)
        numFive = findViewById(R.id.numFive)
        numSix = findViewById(R.id.numSix)
        numSeven = findViewById(R.id.numSeven)
        numEight = findViewById(R.id.numEight)
        numNine = findViewById(R.id.numNine)
        // Operators
        divideOper = findViewById(R.id.divideOper)
        multiplyOper = findViewById(R.id.multiplyOper)
        minusOper = findViewById(R.id.minusOper)
        addOper = findViewById(R.id.addOper)
        clrBtn = findViewById(R.id.clrBtn)
        equalOper = findViewById(R.id.equalOper)
        decimalOper = findViewById(R.id.decimalOper)

        numberZero?.setOnClickListener {onDigit(numOne!!) }
        numOne?.setOnClickListener {onDigit(numOne!!) }
        numTwo?.setOnClickListener {onDigit(numTwo!!) }
        numThree?.setOnClickListener { onDigit(numThree!!) }
        numFour?.setOnClickListener { onDigit(numFour!!) }
        numFive?.setOnClickListener { onDigit(numFive!!) }
        numSix?.setOnClickListener {onDigit(numSix!!) }
        numSeven?.setOnClickListener { onDigit(numSeven!!) }
        numEight?.setOnClickListener {onDigit(numEight!!) }
        numNine?.setOnClickListener {onDigit(numNine!!) }

        clrBtn?.setOnClickListener { onClear(clrBtn!!) }
        divideOper?.setOnClickListener { onOperator(divideOper!!) }
        multiplyOper?.setOnClickListener { onOperator(multiplyOper!!) }
        minusOper?.setOnClickListener { onOperator(minusOper!!) }
        addOper?.setOnClickListener { onOperator(addOper!!) }
        decimalOper?.setOnClickListener { onDecimalPoint(decimalOper!!) }
        equalOper?.setOnClickListener { onEqual(equalOper!!) }
    }
    // Will Reflect to Any numbers that was clicked
    private fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true // Numeric will be the last
        lastDot = false // Decimal point will be false
    }
    // If the user wants to use decimal point in equation.
    private fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot){ // To trigger, numeric must be the last text and not dot to ensure no multiple decimal point
            tvInput?.append((view as Button).text) // print decimal and change the lastNumeric and lastDot
            lastNumeric = false
            lastDot = true
        }
    }
    // If the user want to use an operator to perform equation
    private fun onOperator(view: View) {
        tvInput?.text?.let { // check if text is not null, therefore we cannot do the equation
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text) // Append the chosen operator and change the last numeric and lastDot
                lastNumeric = false
                lastDot = false
            }
        }

    }
    // When the user want to know the answer in the given equation
    private fun onEqual(view: View) {
        if (lastNumeric){ // Check if the last text is numeric
            try {
                var tvValue = tvInput?.text.toString() // Store the content of tvInput in tvValue
                var prefix = ""
                if(tvValue.startsWith("-")){ // Check if the first text in the text is an operator
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }else if(tvValue.startsWith("*")){ // Check if the first text in the text is an operator
                    prefix = "*"
                    tvValue = tvValue.substring(1)
                }
                else if(tvValue.startsWith("/")){ // Check if the first text in the text is an operator
                    prefix = "/"
                    tvValue = tvValue.substring(1)
                }
                else if(tvValue.startsWith("+")){ // Check if the first text in the text is an operator
                    prefix = "+"
                    tvValue = tvValue.substring(1)
                }
                // Split the equation between the operators
                if (tvValue.contains("-")){
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterEquation((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                }else if(tvValue.contains("*")){
                    var splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterEquation((one.toDouble() / two.toDouble()).toString())
                }
                else if(tvValue.contains("+")){
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterEquation((one.toDouble() + two.toDouble()).toString())
                }
                if (tvValue.contains("*")) {
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    // Print the output of the equation and remove the .0
                    tvInput?.text = removeZeroAfterEquation((one.toDouble() * two.toDouble()).toString())
                }


            }catch (e: java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    // Call this function to check if there's already and operator in the equation, otherwise, equation will not proceed
    private fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
    // Clear the text
    private fun onClear(view: View){
        tvInput?.text = ""
    }
    // Remove the .0 in the text
    private fun removeZeroAfterEquation(result: String): String{
        var value = result
        if(value.contains(".0")){
            value = result.substring(0, result.length-2)
            return value
        }
        return value
    }

}