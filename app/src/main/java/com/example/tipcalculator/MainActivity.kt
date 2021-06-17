package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

        binding.roundOf.setOnCheckedChangeListener { _, _ -> calculateTip()  }
    }


    private fun calculateTip() {
        val text = binding.costOfServiceEditText.text.toString()
        val cost = text.toDoubleOrNull()
        if (cost == null){
            binding.amount.text=""
            return
        }

        val tipPer = when(binding.RadioGrp.checkedRadioButtonId){
            R.id.option_20 -> 0.20
            R.id.option_15 -> 0.15
            else -> 0.10
        }

        var totalTip= tipPer * cost

        if(binding.roundOf.isChecked){
            totalTip = kotlin.math.ceil(totalTip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(totalTip)
        binding.amount.text = getString(R.string.tip_amount, formattedTip)


    }
}