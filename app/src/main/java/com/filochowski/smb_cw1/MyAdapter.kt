package com.filochowski.smb_cw1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filochowski.smb_cw1.databinding.ListElementBinding

class MyAdapter(val dane: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root)

    // w momencie kiedy tworzony jest nowy view holder, bo na przykład pojawia się nowy element w liście
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListElementBinding.inflate(inflater)
        return MyViewHolder(binding)
    }

    // element jest stworzony, teraz bedzie podpięty
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tv3.text = dane[position]
    }

    override fun getItemCount(): Int = dane.size

}