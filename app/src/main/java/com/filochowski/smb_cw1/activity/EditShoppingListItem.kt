package com.filochowski.smb_cw1.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.filochowski.smb_cw1.databinding.ActivityEditShoppingListItemBinding
import com.filochowski.smb_cw1.databinding.ActivityMainBinding

class EditShoppingListItem : AppCompatActivity() {

    private lateinit var binding: ActivityEditShoppingListItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditShoppingListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id = intent.getLongExtra("gotToEditText_id", 0)

        binding.etNameUpd.setText(intent.getCharSequenceExtra("gotToEditText_name"))
        binding.etPriceUpd.setText(intent.getFloatExtra("gotToEditText_price", 0.0f).toString())
        binding.etQuantityUpd.setText(
            intent.getFloatExtra("gotToEditText_quantity", 0.0f).toString()
        )

        binding.button3.setOnClickListener {
            updateItem()
            Toast.makeText(this, "Updated item", Toast.LENGTH_LONG).show()
        }
    }


    fun updateItem() {
        var id = intent.getLongExtra("gotToEditText_id", 0)
        var bought = intent.getBooleanExtra("gotToEditText_bought", false)
        var price = binding.etPriceUpd.text.toString().toFloat()
        var quantity = binding.etQuantityUpd.text.toString().toFloat()
        var name = binding.etNameUpd.text.toString()

        var intentData = Intent()
        intentData.putExtra("gotToEditText_id", id)
        intentData.putExtra("gotToEditText_name", name)
        intentData.putExtra("gotToEditText_price", price)
        intentData.putExtra("gotToEditText_quantity", quantity)
        intentData.putExtra("gotToEditText_bought", bought)

        setResult(Activity.RESULT_OK, intentData)
        finish()
    }
}