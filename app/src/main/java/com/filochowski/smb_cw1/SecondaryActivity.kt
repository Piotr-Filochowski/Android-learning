package com.filochowski.smb_cw1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.filochowski.smb_cw1.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        binding.textView2.text = intent.getCharSequenceExtra("textView1:text")
    }
}