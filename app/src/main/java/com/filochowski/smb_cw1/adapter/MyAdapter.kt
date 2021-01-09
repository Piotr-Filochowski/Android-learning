package com.filochowski.smb_cw1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filochowski.smb_cw1.databinding.ListElementBinding
import com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto
import com.filochowski.smb_cw1.viewmodel.NewViewModel

class MyAdapter(val viewModel: NewViewModel) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var sliList =  emptyList<ShoppingListItemFirebaseDto>()
    var listener: OnClickListener? = null
    class MyViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvId.text = sliList[position].id.toString()
        holder.binding.tvName.text = sliList[position].name
        holder.binding.tvQuantity.text = sliList[position].quantity.toString()
        holder.binding.tvPrice.text = sliList[position].price.toString()
        holder.binding.cbAbsolwent.isChecked = sliList[position].bought!!
        holder.binding.root.setOnClickListener {

            if(listener != null && position != RecyclerView.NO_POSITION) {
                listener!!.onItemClick(sliList[position])
            }
        }
        holder.binding.cbAbsolwent.setOnClickListener {
            sliList[position].bought = holder.binding.cbAbsolwent.isChecked
            viewModel.updateShoppingListItem(sliList[position])
            notifyDataSetChanged()
        }
    }

    fun getShoppingListItemAt(position: Int): ShoppingListItemFirebaseDto {
        return sliList[position]
    }

    override fun getItemCount(): Int = sliList.size

    fun setListOfShoppingListItems(list: List<ShoppingListItemFirebaseDto>) {
        sliList = list
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: ShoppingListItemFirebaseDto?)
    }

    fun setOnItemClickListener(clickListener: OnClickListener) {
        listener = clickListener
    }
}