package com.filochowski.smb_cw1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filochowski.smb_cw1.viewmodel.StudentViewModel
import com.filochowski.smb_cw1.databinding.ListElementBinding
import com.filochowski.smb_cw1.entity.StudentEntity

class MyAdapter(val viewModel: StudentViewModel) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var studentList =  emptyList<StudentEntity>()

    class MyViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvId.text = studentList[position].id.toString()
        holder.binding.tvName.text = studentList[position].name
        holder.binding.tvSurname.text = studentList[position].surname
        holder.binding.cbAbsolwent.isChecked = studentList[position].graduated
        holder.binding.root.setOnClickListener {
            viewModel.deleteStudent(studentList[position])
        }
        holder.binding.cbAbsolwent.setOnClickListener {
            studentList[position].graduated = holder.binding.cbAbsolwent.isChecked
            viewModel.updateStudent(studentList[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = studentList.size

    fun setListStudent(list: List<StudentEntity>) {
        studentList = list
        notifyDataSetChanged()
    }

}