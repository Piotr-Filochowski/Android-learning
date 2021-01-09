package com.filochowski.smb_cw1.activity

import android.app.Activity
import android.content.ComponentName
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
import com.filochowski.smb_cw1.R
import com.filochowski.smb_cw1.adapter.MyAdapter
import com.filochowski.smb_cw1.databinding.ActivitySecondaryBinding
import com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto
import com.filochowski.smb_cw1.entity.ShoppingListItem
import com.filochowski.smb_cw1.viewmodel.ShoppingListItemViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SecondaryActivity : AppCompatActivity() {

    private lateinit var viewModelGlobal: ShoppingListItemViewModel
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val viewModel = ShoppingListItemViewModel(application)
        viewModelGlobal = viewModel
        database = FirebaseDatabase.getInstance().getReference("ShoppingListItem")
        val adapter = MyAdapter(viewModel)
        viewModel.allItems.observe(this, Observer {
            it.let {
                adapter.setListOfShoppingListItems(it)
            }
        })

//      Layout manager
        binding.rv1.layoutManager = LinearLayoutManager(this)
        // Optional DividerItemDecoration
        binding.rv1.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        // Adaoter
        binding.rv1.adapter = adapter

        var myFalse = false

        binding.button2.setOnClickListener {
            if(binding.etName.text.isEmpty() || binding.etPrice.text.isEmpty() || binding.etQuantity.text.isEmpty()){
                Toast.makeText(this, "Fill all fields first", Toast.LENGTH_SHORT).show()
            } else {
                var item = ShoppingListItem(
                    name = binding.etName.text.toString(),
                    quantity = binding.etQuantity.text.toString().toFloat(),
                    price = binding.etPrice.text.toString().toFloat()
                )
                var id = viewModel.addShoppingItem(item)
                var idFirebase = database.push().key
                if(idFirebase != null){
                    var fDto = ShoppingListItemFirebaseDto(
                        idFirebase,
                        id,
                        binding.etName.text.toString(),
                        binding.etQuantity.text.toString().toFloat(),
                        binding.etPrice.text.toString().toFloat()
                    )
                    database.child(idFirebase).setValue(fDto)
                }
                binding.etName.setText("")
                binding.etPrice.setText("")
                binding.etQuantity.setText("")
            }
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
                    viewModel.deleteShoppingListItem(adapter.getShoppingListItemAt(viewHolder.adapterPosition))
                    Toast.makeText(context, "Deleted item", Toast.LENGTH_SHORT).show()
                }
            }).attachToRecyclerView(binding.rv1)

        val onClickListener = object : MyAdapter.OnClickListener{
            override fun onItemClick(item: ShoppingListItem?) {
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            var id = data!!.getLongExtra("gotToEditText_id", 0)
            var name = data!!.getCharSequenceExtra("gotToEditText_name").toString()
            var price = data!!.getFloatExtra("gotToEditText_price", 0.0f)
            var quantity = data!!.getFloatExtra("gotToEditText_quantity", 0.0f)
            var bought = data!!.getBooleanExtra("gotToEditText_bought", false)
            var shoppingListItem = ShoppingListItem(id, name,  quantity, price, bought)
            viewModelGlobal.updateShoppingListItem(shoppingListItem)

        }
    }
}
