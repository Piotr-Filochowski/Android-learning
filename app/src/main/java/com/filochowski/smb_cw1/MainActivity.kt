package com.filochowski.smb_cw1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    }

    override fun onStart() {
        super.onStart()
        binding.someText.text = sharedPreferences.getString("tv_text", getString(R.string.nothing_was_saved_before))
    }


    override fun onStop() {
        super.onStop()
        val editor = sharedPreferences.edit()
        editor.putString("tv_text", binding.someText.text.toString())
        editor.apply()
    }
}