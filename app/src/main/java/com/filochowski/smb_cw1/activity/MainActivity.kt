package com.filochowski.smb_cw1.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.filochowski.smb_cw1.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    private var isColorOn: Boolean = false
    private var isBigSize: Boolean = false

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            binding.tVLogged.text = "Logged as: " + currentUser.email
        } else {
            binding.tVLogged.text = "Logged as: ?"
        }

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

        binding.buttonCommonList.setOnClickListener {
            val intent1 = Intent(this, CommonListActivity::class.java)
            startActivity(intent1)
        }

    }

    override fun onStart() {
        super.onStart()

        var color = sharedPreferences.getBoolean("color", true)
        var size = sharedPreferences.getBoolean("size", true)
        if(color){
            binding.buttonOptions.setBackgroundColor(Color.BLUE)
            binding.buttonNext .setBackgroundColor(Color.BLUE)
            isColorOn = color
        } else {
            binding.buttonOptions.setBackgroundColor(Color.RED)
            binding.buttonNext.setBackgroundColor(Color.RED)
            isColorOn = color
        }

        if(size){
            binding.buttonOptions.setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
            binding.buttonNext .setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
            isBigSize = size
        } else {
            binding.buttonOptions.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
            binding.buttonNext .setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
            isBigSize = size
        }
    }


    override fun onStop() {
        super.onStop()
        val editor = sharedPreferences.edit()
        editor.putBoolean("color", isColorOn)
        editor.putBoolean("size", isBigSize)
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

            if(size){
                binding.buttonOptions.setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
                binding.buttonNext .setTextSize(TypedValue.COMPLEX_UNIT_SP,28.0f)
                isBigSize = size
            } else {
                binding.buttonOptions.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
                binding.buttonNext .setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
                isBigSize = size
            }

        }
        val editor = sharedPreferences.edit()
        editor.putBoolean("color", isColorOn)
        editor.putBoolean("size", isBigSize)
        editor.apply()
    }
}