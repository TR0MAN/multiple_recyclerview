package com.example.recyclerview_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_test.databinding.MultyElementBinding
import com.example.recyclerview_test.databinding.SingleElementBinding
import java.lang.IllegalStateException

class ItemAdapter(
    private val clickListener: ItemListener
): ListAdapter<Item, ItemAdapter.BaseHolder>(DiffUtilItem()) {

    var isMultiState = false

    abstract class BaseHolder(view: View): RecyclerView.ViewHolder(view) {
        abstract fun bind(item: Item)
    }

    inner class ItemViewHolder(private val binding: SingleElementBinding
    ): BaseHolder(binding.root) {

        override fun bind(item: Item) {
            with(binding) {
                tvUserId.text = "[ID = ${item.id}]"
                tvUserName.text = item.name
                tvUserCareer.text = item.career
                imgUserAvatar.setImageResource(R.drawable.element_rv_default_avatar)

                root.setOnLongClickListener {
                    clickListener.longItemClick(item = item)
                    return@setOnLongClickListener true
                }
            }
        }
    }

    inner class ItemViewHolderMultiple(private val binding: MultyElementBinding
    ): BaseHolder(binding.root) {

        override fun bind(item: Item) {
            with(binding) {
                tvUserId.text = "[ID = ${item.id}]"
                tvUserName.text = item.name
                tvUserCareer.text = item.career
                imgUserAvatar.setImageResource(R.drawable.element_rv_default_avatar)

                root.setOnLongClickListener {
                    clickListener.longItemClick(item = item)
                    return@setOnLongClickListener true
                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isMultiState) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder =
        when (viewType) {
            0 -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = SingleElementBinding.inflate(inflater, parent, false)
                ItemViewHolder(binding)
            }
            1 -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = MultyElementBinding.inflate(inflater, parent, false)
                ItemViewHolderMultiple(binding)
            }
            else -> throw IllegalStateException()
        }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(getItem(position))
    }

}