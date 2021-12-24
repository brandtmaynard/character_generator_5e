package com.example.charactergenerator5e.ui.generator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.charactergenerator5e.databinding.AlignmentEntryItemBinding
import com.example.charactergenerator5e.databinding.CategoryEntryItemBinding
import com.example.charactergenerator5e.databinding.SubcategoryEntryItemBinding

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Entry>?) {
    val adapter = recyclerView.adapter as EntryAdapter
    adapter.submitList(data)
}

class EntryAdapter(private val entryList: List<Entry>) : ListAdapter<Entry, RecyclerView.ViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Entry>() {
        override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem == newItem
        }

        private const val TYPE_CATEGORY = 1
        private const val TYPE_SUBCATEGORY = 2
        private const val TYPE_ALIGNMENT = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_CATEGORY -> CategoryEntryViewHolder(CategoryEntryItemBinding.inflate(
                LayoutInflater.from(parent.context)))
            TYPE_SUBCATEGORY -> SubcategoryEntryViewHolder(
                SubcategoryEntryItemBinding.inflate(
                    LayoutInflater.from(parent.context)))
            TYPE_ALIGNMENT -> AlignmentEntryViewHolder(AlignmentEntryItemBinding.inflate(
                LayoutInflater.from(parent.context)))
            else -> throw IllegalArgumentException("Invalid viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val entry = getItem(position)) {
            is CategoryEntry ->
                if (entryList[0].name == "LG") {
                    (holder as AlignmentEntryViewHolder).bind(entry)
                } else {
                    (holder as CategoryEntryViewHolder).bind(entry, this)
                }
            is SubcategoryEntry -> (holder as SubcategoryEntryViewHolder).bind(entry, this)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (entryList[0].name == "LG") {
            return TYPE_ALIGNMENT
        }
        return when (entryList[position]) {
            is CategoryEntry -> TYPE_CATEGORY
            is SubcategoryEntry -> TYPE_SUBCATEGORY
        }
    }

    fun updateParent(position: Int, childChecked: Boolean) {
        if ((childChecked && !entryList[position].enabled.get()) || (!childChecked && !parentHasActiveChildren(position))) {
            entryList[position].enabled.set(childChecked)
        }
    }

    fun updateSubList(enabled: Boolean, startPosition: Int, endPosition: Int) {
        entryList.subList(startPosition, endPosition).forEach {it.enabled.set(enabled)}
    }

    fun parentHasActiveChildren(position: Int): Boolean {
        return entryList.subList(position + 1, position + 1 + (entryList[position] as CategoryEntry).numberOfChildren).any { it.enabled.get() }
    }

    fun parentHasInactiveChildren(position: Int): Boolean {
        return entryList.subList(position + 1, position + 1 + (entryList[position] as CategoryEntry).numberOfChildren).any { !it.enabled.get() }
    }

    class CategoryEntryViewHolder(private var binding: CategoryEntryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: CategoryEntry, entryAdapter: EntryAdapter) {
            binding.entry = entry
            if (entry.numberOfChildren > 0) {
                binding.entryCheckBox.setOnClickListener {
                    if ((binding.entryCheckBox.isChecked || entryAdapter.parentHasInactiveChildren(adapterPosition))) {
                        entryAdapter.updateSubList(true, adapterPosition, adapterPosition + 1 + entry.numberOfChildren)
                    } else {
                        entryAdapter.updateSubList(false, adapterPosition, adapterPosition + 1 + entry.numberOfChildren)
                    }
                }
            } else {
                binding.entryCheckBox.setOnClickListener {}
            }
            binding.executePendingBindings()
        }
    }

    class SubcategoryEntryViewHolder(private var binding: SubcategoryEntryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: SubcategoryEntry, entryAdapter: EntryAdapter) {
            binding.entry = entry
            binding.entryCheckBox.setOnClickListener {
                entryAdapter.updateParent(entry.parentIndex, binding.entryCheckBox.isChecked)
            }
            binding.executePendingBindings()
        }
    }

    class AlignmentEntryViewHolder(private var binding: AlignmentEntryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: CategoryEntry) {
            binding.entry = entry
            binding.executePendingBindings()
        }
    }
}