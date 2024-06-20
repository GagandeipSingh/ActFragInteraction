package com.example.actfraginteraction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.actfraginteraction.MainActivity
import com.example.actfraginteraction.R
import com.example.actfraginteraction.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment(), FrInterface {
    private lateinit var mainAct : MainActivity
    private lateinit var binding : FragmentFirstBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainAct = activity as MainActivity
        mainAct.interfaceVar = this
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.passValue.setOnClickListener {
            if(binding.etValue.text.toString() == ""){
                binding.etLayout.error = "Enter Value.."
            }
            else{
                mainAct.valueFromFrag(binding.etValue.text.toString())
            }
        }
        binding.etValue.doOnTextChanged { _,_,_,_->
            binding.etLayout.error = null
            mainAct.changeFromFrag("reset","0")
        }
        binding.increment.setOnClickListener {
            val etText = binding.etValue.text.toString()
            if(etText == "") binding.etLayout.error = "Enter Value.."
            else mainAct.changeFromFrag("inc",etText)
        }
        binding.decrement.setOnClickListener {
            val etText = binding.etValue.text.toString()
            if(etText == "") binding.etLayout.error = "Enter Value.."
            else mainAct.changeFromFrag("dec",etText)
        }
        binding.reset.setOnClickListener {
            binding.etValue.text = null
            binding.etValue.clearFocus()
            mainAct.changeFromFrag("reset","0")
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun valueFromAct(etTextAct: String) {
        binding.etValue.setText(etTextAct)
    }

    override fun changeColor(whichBtn: String) {
        when(whichBtn){
            "Red" -> {
                binding.mainLayout.setBackgroundResource(R.drawable.redfragbg)
            }
            "Green" -> {
                binding.mainLayout.setBackgroundResource(R.drawable.greenfragbg)
            }
            "Blue" -> {
                binding.mainLayout.setBackgroundResource(R.drawable.bluefragbg)
            }
        }
    }
}