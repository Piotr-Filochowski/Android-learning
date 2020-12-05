package com.filochowski.smb_cw1

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.filochowski.smb_cw1.databinding.ActivitySecondaryBinding
import kotlinx.android.synthetic.main.activity_secondary.view.*

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        binding.textView2.text = intent.getCharSequenceExtra("textView1Text")


        val viewModel = StudentViewModel(application)
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
                StudentEntity(
                    name = binding.etName.text.toString(),
                    surname = binding.etSurname.text.toString(),
                    graduated = binding.checkBox.isChecked
                )
            )
        }

        binding.button2.setOnLongClickListener{
            viewModel.removeAll()
            true
        }

    }
}
