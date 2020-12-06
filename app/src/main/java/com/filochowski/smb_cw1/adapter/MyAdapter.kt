package com.filochowski.smb_cw1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filochowski.smb_cw1.databinding.ListElementBinding
import com.filochowski.smb_cw1.entity.ShoppingListItem
import com.filochowski.smb_cw1.viewmodel.ShoppingListItemViewModel

class MyAdapter(val viewModel: ShoppingListItemViewModel) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var studentList =  emptyList<ShoppingListItem>()

    var listener: OnClickListener? = null


    class MyViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvId.text = studentList[position].id.toString()
        holder.binding.tvName.text = studentList[position].name
        holder.binding.tvQuantity.text = studentList[position].quantity.toString()
        holder.binding.tvPrice.text = studentList[position].price.toString()
        holder.binding.cbAbsolwent.isChecked = studentList[position].bought
        holder.binding.root.setOnClickListener {

            if(listener != null && position != RecyclerView.NO_POSITION) {
                listener!!.onItemClick(studentList[position])
            }
        }
        holder.binding.cbAbsolwent.setOnClickListener {
            studentList[position].bought = holder.binding.cbAbsolwent.isChecked
            viewModel.updateStudent(studentList[position])
            notifyDataSetChanged()
        }
    }

    fun getShoppingListItemAt(position: Int): ShoppingListItem {
        return studentList[position]
    }

    override fun getItemCount(): Int = studentList.size

    fun setListStudent(list: List<ShoppingListItem>) {
        studentList = list
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: ShoppingListItem?)
    }

    fun setOnItemClickListener(clickListener: OnClickListener) {
        listener = clickListener
    }
}