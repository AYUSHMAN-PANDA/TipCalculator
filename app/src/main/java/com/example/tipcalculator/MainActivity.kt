package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val text = binding.costOfService.text.toString()
        val cost = text.toDoubleOrNull()
        if (cost == null){
            binding.amount.text=""
            return
        }

        val tip = binding.RadioGrp.checkedRadioButtonId
        val tipPer = when(tip){
            R.id.option_20 -> 0.20
            R.id.option_15 -> 0.15
            else -> 0.10
        }

        var totalTip= tipPer * cost

        val roundUp = binding.roundOf.isChecked
        if(roundUp){
            totalTip = kotlin.math.ceil(totalTip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(totalTip)
        binding.amount.text = getString(R.string.tip_amount, formattedTip)


    }
}