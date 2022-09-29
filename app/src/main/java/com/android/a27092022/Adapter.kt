package com.android.a27092022

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.a27092022.databinding.ItemBinding

class Adapter : ListAdapter<ModelItem, Adapter.MyViewHolder>(diffCallBack) {
    class MyViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<ModelItem>() {
            override fun areItemsTheSame(oldItem: ModelItem, newItem: ModelItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ModelItem, newItem: ModelItem): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=getItem(position)
        holder.binding.apply {
            id.text=item.id.toString()
            body.text=item.body.toString()
            title.text=item.title.toString()
            userId.text=item.userId.toString()
        }
    }
}