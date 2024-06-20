package com.example.actfraginteraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.actfraginteraction.databinding.ActivityMainBinding
import com.example.actfraginteraction.fragments.FrInterface

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var interfaceVar : FrInterface
    private var temp : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.red.setOnClickListener {
            interfaceVar.changeColor("Red")
        }
        binding.green.setOnClickListener {
            interfaceVar.changeColor("Green")
        }
        binding.blue.setOnClickListener {
            interfaceVar.changeColor("Blue")
        }
        binding.passValue.setOnClickListener {
            if(binding.etValue.text.toString() == ""){
                binding.etLayout.error = "Enter Value.."
            }
            else interfaceVar.valueFromAct(binding.etValue.text.toString())
        }
        binding.etValue.doOnTextChanged { _, _, _, _ ->
            binding.etLayout.error = null
        }
    }
    fun valueFromFrag(etFTextFrag : String){
        binding.etValue.setText(etFTextFrag)
    }
    fun changeFromFrag(key : String, value : String){
        if(binding.counter.text.toString() == getString(R.string.cntValue)){
            temp = value.toInt()
        }
        else{
            temp = binding.counter.text.toString().toInt()
        }
        when(key){
            "inc" -> {
                temp = temp!! + 1
                binding.counter.text = temp.toString()
            }
            "dec" ->{
                temp = temp!! - 1
                binding.counter.text = temp.toString()
            }
            "reset" -> binding.counter.text = getString(R.string.cntValue)
        }

    }
}