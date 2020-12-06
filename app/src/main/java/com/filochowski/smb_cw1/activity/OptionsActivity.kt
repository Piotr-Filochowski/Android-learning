package com.filochowski.smb_cw1.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.filochowski.smb_cw1.databinding.ActivityOptionsBinding
import com.filochowski.smb_cw1.entity.ShoppingListItem

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
        binding.optSize.setChecked(optSize)

        binding.optColorChange
//            .setOnCheckedChangeListener { _ , isChecked ->
//            val message = if (isChecked) "Blue" else "Red"
//            val editor = sharedPreferences.edit()
//            editor.putBoolean("color", isChecked)
//            Toast.makeText(this@OptionsActivity, message,
//                Toast.LENGTH_SHORT).show()
//        }

        binding.optSize
//            .setOnCheckedChangeListener { _ , isChecked ->
//            val message = if (isChecked) "Small" else "Big"
//            val editor = sharedPreferences.edit()
//            editor.putBoolean("size", isChecked)
//            Toast.makeText(this@OptionsActivity, message,
//                Toast.LENGTH_SHORT).show()
//        }

        binding.button.setOnClickListener {
            var intentData = Intent()
            intentData.putExtra("opt_color", binding.optColorChange.isChecked)
            intentData.putExtra("opt_size", binding.optSize.isChecked)
            setResult(Activity.RESULT_OK, intentData)
            finish()
        }


    }
}