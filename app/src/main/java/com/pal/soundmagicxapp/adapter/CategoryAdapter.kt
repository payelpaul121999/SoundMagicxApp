package com.pal.soundmagicxapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pal.soundmagicxapp.model.CategoryModel
import com.pal.soundmagicxapp.databinding.CategoryItemLayoutBinding

class CategoryAdapter(private val categoryList: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var onItemClick: ((CategoryModel) -> Unit)? = null
    fun setOnItemClickListener(listener: (CategoryModel) -> Unit) {
        onItemClick = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(private val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryModel) {
            binding.apply {
                Glide.with(itemView)
                    .load(category.imgUrl)
                    .centerCrop()
                    .into(imgCategory)
                textViewCategory.text = category.name
                root.setOnClickListener {
                    onItemClick?.invoke(category)
                }
            }
        }
    }
}
