package com.filochowski.smb_cw1.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.filochowski.smb_cw1.R
import com.filochowski.smb_cw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            binding.someText.text = binding.editText1.text
            Toast.makeText(this, binding.editText1.text, Toast.LENGTH_LONG).show()
        }

        binding.buttonNext.setOnClickListener {
            val intent1 = Intent(this, SecondaryActivity::class.java)
            intent1.putExtra("textView1Text", binding.editText1.text)
            startActivity(intent1)
        }



        binding.changeColour.setOnCheckedChangeListener { _ , isChecked ->
            val message = if (isChecked) "Blue" else "Red"
            if (isChecked) {
                binding.button.setBackgroundColor(Color.BLUE)
                binding.buttonNext .setBackgroundColor(Color.BLUE)
                binding.changeColour.setTextColor(Color.BLUE)
            } else {
                binding.button.setBackgroundColor(Color.RED)
                binding.buttonNext.setBackgroundColor(Color.RED)
                binding.changeColour.setTextColor(Color.RED)
            }


            Toast.makeText(this@MainActivity, message,
                Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        binding.someText.text = sharedPreferences.getString("tv_text", getString(R.string.nothing_was_saved_before))
        var color = sharedPreferences.getBoolean("color", true)
        if(color){
            binding.button.setBackgroundColor(Color.BLUE)
            binding.buttonNext .setBackgroundColor(Color.BLUE)
            binding.changeColour.setTextColor(Color.BLUE)
            binding.changeColour.setChecked(color)
        } else {
            binding.button.setBackgroundColor(Color.RED)
            binding.buttonNext.setBackgroundColor(Color.RED)
            binding.changeColour.setTextColor(Color.RED)
            binding.changeColour.setChecked(color)
        }
    }


    override fun onStop() {
        super.onStop()
        val editor = sharedPreferences.edit()
        editor.putString("tv_text", binding.someText.text.toString())
        editor.putBoolean("color", binding.changeColour.isChecked)
        editor.apply()
    }
}