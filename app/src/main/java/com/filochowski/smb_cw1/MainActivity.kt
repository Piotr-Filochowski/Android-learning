package com.filochowski.smb_cw1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.filochowski.smb_cw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            binding.someText.text = binding.editText1.text
            Toast.makeText(this, binding.editText1.text, Toast.LENGTH_LONG).show()
        }

        binding.buttonNext.setOnClickListener {
            val intent1 = Intent(this, SecondaryActivity::class.java)
            intent.putExtra("textView1:text", binding.editText1.text)
            startActivity(intent1)
        }

    }
}