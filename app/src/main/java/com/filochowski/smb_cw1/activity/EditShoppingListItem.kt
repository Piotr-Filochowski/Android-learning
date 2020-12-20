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

        //        information passed from broadcast receiver after clicking notification
        var broadcastProductId = intent.getStringExtra("productid")             //  gotToEditText_id  only in updateItem fun
        var broadcastProductName = intent.getStringExtra("productname")         //  gotToEditText_name
        var broadcastProductPrice = intent.getStringExtra("productprice")       //  gotToEditText_price
        var broadcastProductNumber = intent.getStringExtra("productnumber")     //  gotToEditText_quantity
        var broadcastProductIsBought = intent.getStringExtra("productisbought") //  gotToEditText_bought  only in updateItem fun


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