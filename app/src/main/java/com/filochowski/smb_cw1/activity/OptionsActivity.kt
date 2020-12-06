package com.filochowski.smb_cw1.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.filochowski.smb_cw1.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding:  ActivityOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        setContentView(binding.root)

        val intent = intent
        var optColor = intent.getBooleanExtra("opt_color", false)
        var optSize = intent.getBooleanExtra("opt_size", false)

        binding.optColorChange.setChecked(optColor)
        if(optColor){
            binding.button.setBackgroundColor(Color.BLUE)
            binding.optColorChange.setTextColor(Color.BLUE)
            binding.optSize.setTextColor(Color.BLUE)
        } else {
            binding.button.setBackgroundColor(Color.RED)
            binding.optColorChange.setTextColor(Color.RED)
            binding.optSize.setTextColor(Color.RED)
        }
        binding.optSize.setChecked(optSize)
        if(optSize){
            binding.button.setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
            binding.optColorChange.setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
            binding.optSize.setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
        } else {
            binding.button.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
            binding.optColorChange.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
            binding.optSize.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
        }
        binding.optColorChange.setOnCheckedChangeListener { _ , isChecked ->
            val message = if (isChecked) "Blue" else "Red"
            if (isChecked) {
                binding.button.setBackgroundColor(Color.BLUE)
                binding.optColorChange.setTextColor(Color.BLUE)
                binding.optSize.setTextColor(Color.BLUE)
            } else {
                binding.button.setBackgroundColor(Color.RED)
                binding.optColorChange.setTextColor(Color.RED)
                binding.optSize.setTextColor(Color.RED)
            }
        }

        binding.optSize.setOnCheckedChangeListener { _ , isChecked ->
            val message = if (isChecked) "Blue" else "Red"
            if (isChecked) {
                binding.button.setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
                binding.optColorChange.setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
                binding.optSize.setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
            } else {
                binding.button.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
                binding.optColorChange.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
                binding.optSize.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
            }
        }


        binding.button.setOnClickListener {
            var intentData = Intent()
            intentData.putExtra("opt_color", binding.optColorChange.isChecked)
            intentData.putExtra("opt_size", binding.optSize.isChecked)
            setResult(Activity.RESULT_OK, intentData)
            finish()
        }


    }
}