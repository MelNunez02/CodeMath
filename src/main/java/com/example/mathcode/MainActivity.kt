package com.example.mathcode

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etBillAmount: EditText
    private lateinit var spinnerTip: Spinner
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        etBillAmount = findViewById(R.id.etBillAmount)
        spinnerTip = findViewById(R.id.spinnerTip)
        tvResult = findViewById(R.id.tvResult)
        val btnCalculate: Button = findViewById(R.id.btnCalculate)

        // Set up Spinner with Tip Percentages
        val tipOptions = arrayOf("10%", "15%", "20%", "25%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTip.adapter = adapter

        // Set Click Listener on Button
        btnCalculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val billAmountStr = etBillAmount.text.toString()
        if (billAmountStr.isEmpty()) {
            tvResult.text = "Please enter a bill amount."
            return
        }

        val billAmount = billAmountStr.toDouble()
        val selectedTip = spinnerTip.selectedItem.toString().replace("%", "").toDouble() / 100

        val tipAmount = billAmount * selectedTip
        val totalAmount = billAmount + tipAmount

        tvResult.text = String.format("Total with Tip: $%.2f", totalAmount)
    }
}
