package com.filochowski.smb_cw1

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.filochowski.smb_cw1.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        binding.textView2.text = intent.getCharSequenceExtra("textView1Text")



        val elementList = arrayListOf("Element 1", "Element 2 ", "Element 3", "Element 4", "Element 5")


//      Layout manager
        binding.rv1.layoutManager = LinearLayoutManager(this)
        // Optional DividerItemDecoration
        binding.rv1.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        // Adaoter
        binding.rv1.adapter = MyAdapter(elementList)

    }
}
