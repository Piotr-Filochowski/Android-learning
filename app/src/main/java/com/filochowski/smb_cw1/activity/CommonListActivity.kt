package com.filochowski.smb_cw1.activity

import android.app.Activity
import android.content.Intent
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
import com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto
import com.filochowski.smb_cw1.viewmodel.FirebaseCommonItemsViewModel
import com.google.firebase.auth.FirebaseAuth


class CommonListActivity : AppCompatActivity() {

    private lateinit var viewModel: FirebaseCommonItemsViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        viewModel = FirebaseCommonItemsViewModel(application)
        val adapter = MyAdapter(viewModel)

        viewModel.allItems.observe(this, Observer {
            it.let {
                val list : MutableList<ShoppingListItemFirebaseDto> = mutableListOf()
                val children = it!!.children
                for (it in children) {
                    list.add(it.getValue(ShoppingListItemFirebaseDto::class.java)!!)
                }
                adapter.setListOfShoppingListItems(list)
            }
        })

//      Layout manager
        binding.rv1.layoutManager = LinearLayoutManager(this)
        // Optional DividerItemDecoration
        binding.rv1.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        // Adaoter
        binding.rv1.adapter = adapter

        setUpSaveButton(binding, viewModel)
        setUpDeleteOnSwipe(viewModel, adapter, binding)
        setUpAdapter(adapter)

    }

    private fun setUpAdapter(adapter: MyAdapter) {
        val context = this
        val onClickListener = object : MyAdapter.OnClickListener {
            override fun onItemClick(item: ShoppingListItemFirebaseDto?) {
                var intent = Intent(context, EditShoppingListItem::class.java)
                intent.putExtra("gotToEditText_id", item!!.id)
                intent.putExtra("gotToEditText_name", item.name)
                intent.putExtra("gotToEditText_price", item.price)
                intent.putExtra("gotToEditText_quantity", item.quantity)
                intent.putExtra("gotToEditText_bought", item.bought)
                startActivityForResult(intent, 1)
            }
        }

        adapter.setOnItemClickListener(onClickListener)
    }

    private fun setUpDeleteOnSwipe(
        viewModel: FirebaseCommonItemsViewModel,
        adapter: MyAdapter,
        binding: ActivitySecondaryBinding
    ) {
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
                    viewModel.deleteShoppingListItem(adapter.getShoppingListItemAt(viewHolder.adapterPosition))
                    Toast.makeText(context, "Deleted item", Toast.LENGTH_SHORT).show()
                }
            }).attachToRecyclerView(binding.rv1)
    }

    private fun setUpSaveButton(
        binding: ActivitySecondaryBinding,
        viewModel: FirebaseCommonItemsViewModel
    ) {
        binding.button2.setOnClickListener {
            if (binding.etName.text.isEmpty() || binding.etPrice.text.isEmpty() || binding.etQuantity.text.isEmpty()) {
                Toast.makeText(this, "Fill all fields first", Toast.LENGTH_SHORT).show()
            } else {
                var item = ShoppingListItemFirebaseDto(
                    id = "null",
                    name = binding.etName.text.toString(),
                    quantity = binding.etQuantity.text.toString().toFloat(),
                    price = binding.etPrice.text.toString().toFloat(),
                    bought = false
                )
                viewModel.addShoppingItem(item)
                binding.etName.setText("")
                binding.etPrice.setText("")
                binding.etQuantity.setText("")
            }
        }
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                var id = data.getStringExtra("gotToEditText_id").orEmpty()
                var name = data.getCharSequenceExtra("gotToEditText_name").toString()
                var price = data.getFloatExtra("gotToEditText_price", 0.0f)
                var quantity = data.getFloatExtra("gotToEditText_quantity", 0.0f)
                var bought = data.getBooleanExtra("gotToEditText_bought", false)
                var shoppingListItem = ShoppingListItemFirebaseDto(id, name, quantity, price, bought)
                viewModel.updateShoppingListItem(shoppingListItem)
            }

        }
    }
}