package com.filochowski.smb_cw1.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.filochowski.smb_cw1.R
import com.filochowski.smb_cw1.databinding.ActivityMainBinding
import com.filochowski.smb_cw1.entity.ShoppingListItem

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    private var isColorOn: Boolean = false
    private var isBigSize: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        setContentView(binding.root)
        binding.buttonOptions.setOnClickListener {
            val intentOptions = Intent(this, OptionsActivity::class.java)
            intentOptions.putExtra("opt_color", isColorOn)
            intentOptions.putExtra("opt_size", isBigSize)
            startActivityForResult(intentOptions, 3)
        }
        binding.buttonNext.setOnClickListener {
            val intent1 = Intent(this, SecondaryActivity::class.java)
            startActivity(intent1)
        }

    }

    override fun onStart() {
        super.onStart()

        var color = sharedPreferences.getBoolean("color", true)
        if(color){
            binding.buttonOptions.setBackgroundColor(Color.BLUE)
            binding.buttonNext .setBackgroundColor(Color.BLUE)
            isColorOn = color
        } else {
            binding.buttonOptions.setBackgroundColor(Color.RED)
            binding.buttonNext.setBackgroundColor(Color.RED)
            isColorOn = color
        }
    }


    override fun onStop() {
        super.onStop()
        val editor = sharedPreferences.edit()
        editor.putBoolean("color", isColorOn)
        editor.apply()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 3 && resultCode == Activity.RESULT_OK) {
            var color = data!!.getBooleanExtra("opt_color", false)
            var size = data!!.getBooleanExtra("opt_size", false)

            if(color){
                binding.buttonOptions.setBackgroundColor(Color.BLUE)
                binding.buttonNext .setBackgroundColor(Color.BLUE)
                isColorOn = color
            } else {
                binding.buttonOptions.setBackgroundColor(Color.RED)
                binding.buttonNext.setBackgroundColor(Color.RED)
                isColorOn = color
            }
        }
        val editor = sharedPreferences.edit()
        editor.putBoolean("color", isColorOn)
        editor.apply()
    }
}