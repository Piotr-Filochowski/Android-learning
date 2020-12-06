package com.filochowski.smb_cw1.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.filochowski.smb_cw1.adapter.MyAdapter
import com.filochowski.smb_cw1.databinding.ActivitySecondaryBinding
import com.filochowski.smb_cw1.entity.ShoppingListItem
import com.filochowski.smb_cw1.viewmodel.ShoppingListItemViewModel


class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        binding.textView2.text = intent.getCharSequenceExtra("textView1Text")

        val viewModel = ShoppingListItemViewModel(application)
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
                    quantity = binding.etQuantity.text.toString().toFloat(),
                    price = binding.etPrice.text.toString().toFloat()
                )
            )
        }

        binding.button2.setOnLongClickListener {
            viewModel.removeAll()
            true
        }

        val context = this

        val mIth = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder, target: ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                    viewModel.deleteStudent(adapter.getShoppingListItemAt(viewHolder.adapterPosition))
                    Toast.makeText(context, "Deleted item", Toast.LENGTH_SHORT).show()
                }
            }).attachToRecyclerView(binding.rv1)

        val onClickListener = object : MyAdapter.OnClickListener{
            override fun onItemClick(item: ShoppingListItem?) {
                binding.etName.setText("dupa")
            }
        }

        adapter.setOnItemClickListener(onClickListener)

    }
}
