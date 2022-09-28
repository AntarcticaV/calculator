package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Numbers
        with(binding){
            tvOne.setOnClickListener { appendOnExpresstion("1", true) }
            tvTwo.setOnClickListener { appendOnExpresstion("2", true) }
            tvThree.setOnClickListener { appendOnExpresstion("3", true) }
            tvFour.setOnClickListener { appendOnExpresstion("4", true) }
            tvFive.setOnClickListener { appendOnExpresstion("5", true) }
            tvSix.setOnClickListener { appendOnExpresstion("6", true) }
            tvSeven.setOnClickListener { appendOnExpresstion("7", true) }
            tvEight.setOnClickListener { appendOnExpresstion("8", true) }
            tvNine.setOnClickListener { appendOnExpresstion("9", true) }
            tvZero.setOnClickListener { appendOnExpresstion("0", true) }
            tvDot.setOnClickListener { appendOnExpresstion(".", true) }

            //Operators
            tvPlus.setOnClickListener { appendOnExpresstion("+", false) }
            tvMinus.setOnClickListener { appendOnExpresstion("-", false) }
            tvMul.setOnClickListener { appendOnExpresstion("*", false) }
            tvDivide.setOnClickListener { appendOnExpresstion("/", false) }
            tvOpen.setOnClickListener { appendOnExpresstion("(", false) }
            tvClose.setOnClickListener { appendOnExpresstion(")", false) }

            tvClear.setOnClickListener {
                tvExpression.text = ""
                tvResult.text = ""
            }

            tvBack.setOnClickListener {
                val string = tvExpression.text.toString()
                if(string.isNotEmpty()){
                    tvExpression.text = string.substring(0,string.length-1)
                }
                tvResult.text = ""
            }

            tvEquals.setOnClickListener {
                try {

                    val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if(result == longResult.toDouble())
                        tvResult.text = longResult.toString()
                    else
                        tvResult.text = result.toString()

                }catch (e:Exception){
                    Log.d("Exception"," message : " + e.message )
                }
            }
        }


    }

    fun appendOnExpresstion(string: String, canClear: Boolean) {

        if(binding.tvResult.text.isNotEmpty()){
            binding.tvExpression.text = ""
        }

        if (canClear) {
            binding.tvResult.text = ""
            binding.tvExpression.append(string)
        } else {
            binding.tvExpression.append(binding.tvResult.text)
            binding.tvExpression.append(string)
            binding.tvResult.text = ""
        }
    }
}


