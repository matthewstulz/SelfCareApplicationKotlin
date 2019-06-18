package com.github.stulzm2.selfcareapplicationkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.stulzm2.selfcareapplicationkotlin.R
import com.github.stulzm2.selfcareapplicationkotlin.model.Category

class CategoryAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var categoryClickListener: CategoryAdapterOnItemClickHandler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view_category_item, parent, false)
        return CategoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View) {
            categoryClickListener.onItemClick(categories[adapterPosition])
        }

        private var categoryTitle: TextView? = null
        private var categoryThumbnail: ImageView? = null

        init {
            categoryTitle = itemView.findViewById(R.id.text_view_category_title)
            categoryThumbnail = itemView.findViewById(R.id.image_View_category_image)

        }

        fun bind(category: Category) {
            categoryTitle?.text = category.title
            categoryThumbnail?.setImageResource(category.thumbnail)
            itemView.setOnClickListener(this)
        }
    }

    fun setOnItemClickListener(clickListener: CategoryAdapterOnItemClickHandler) {
        categoryClickListener = clickListener
    }

    interface CategoryAdapterOnItemClickHandler {
        fun onItemClick(category: Category)
    }
}