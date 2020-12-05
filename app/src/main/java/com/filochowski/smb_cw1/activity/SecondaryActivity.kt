package com.filochowski.smb_cw1.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.filochowski.smb_cw1.adapter.MyAdapter
import com.filochowski.smb_cw1.viewmodel.ShoppingListItemViewModel
import com.filochowski.smb_cw1.databinding.ActivitySecondaryBinding
import com.filochowski.smb_cw1.entity.ShoppingListItem

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        binding.textView2.text = intent.getCharSequenceExtra("textView1Text")


        val viewModel =
            ShoppingListItemViewModel(application)
        val adapter = MyAdapter(viewModel)
        viewModel.allStudens.observe(this, Observer {
            it.let {
                adapter.setListStudent(it)
            }
        })


//      Layout manager
        binding.rv1.layoutManager = LinearLayoutManager(this)
        // Optional DividerItemDecoration
        binding.rv1.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        // Adaoter
        binding.rv1.adapter = adapter

        binding.button2.setOnClickListener {
            viewModel.addStudent(
                ShoppingListItem(
                    name = binding.etName.text.toString(),
                    quantity =  binding.etQuantity.text.toString().toFloat(),
                    price = binding.etPrice.text.toString().toFloat()
                )
            )
        }

        binding.button2.setOnLongClickListener{
            viewModel.removeAll()
            true
        }

    }
}
