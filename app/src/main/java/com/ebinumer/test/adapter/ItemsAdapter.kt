package com.ebinumer.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ebinumer.test.R
import com.ebinumer.test.data.LstItemDataModel
import com.ebinumer.test.databinding.RecyclerItemBinding

class ItemsAdapter(
    var itemList: MutableList<LstItemDataModel>,
    //we can get recyclerview click in activity or fragment using lambda fun as below
    val onClick: (data: LstItemDataModel, position: Int ) -> Unit
) :
    RecyclerView.Adapter<ItemsAdapter.itemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return itemViewHolder(
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size


    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        itemList[position].let {it1 ->
            holder.bind(it1)
            holder.itemView.setOnClickListener { onClick(it1,position) }
        }
    }

    class itemViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: LstItemDataModel) {
            binding.apply {
                data = user

            }
        }
    }
}