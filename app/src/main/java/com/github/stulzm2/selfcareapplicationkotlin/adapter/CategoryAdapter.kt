package com.github.stulzm2.selfcareapplicationkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.stulzm2.selfcareapplicationkotlin.R
import com.github.stulzm2.selfcareapplicationkotlin.model.Category
import com.google.android.material.snackbar.Snackbar

class CategoryAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view_category_item, parent, false)
        return CategoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var categoryTitle: TextView? = null

        init {
            categoryTitle = itemView.findViewById(R.id.text_view_category_title)

            itemView.setOnClickListener { v: View ->
                val title: String = categories[adapterPosition].title
                Snackbar.make(v, "Clicked on item $title", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
        }

        fun bind(category: Category) {
            categoryTitle?.text = category.title
        }
    }

}