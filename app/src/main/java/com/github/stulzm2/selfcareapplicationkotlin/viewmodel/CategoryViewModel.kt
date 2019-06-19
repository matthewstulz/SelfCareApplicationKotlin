package com.github.stulzm2.selfcareapplicationkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.github.stulzm2.selfcareapplicationkotlin.R
import com.github.stulzm2.selfcareapplicationkotlin.model.Category

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var categories: MutableList<Category>

    fun getCategories(): MutableList<Category> {
        if (!this::categories.isInitialized) {
            categories = mutableListOf()
            loadCategories()
        }
        return categories
    }

    private fun loadCategories() {
        categories.add(Category("Meditation", R.drawable.meditation))
        categories.add(Category("Journal", R.drawable.journal))
        categories.add(Category("Love", R.drawable.love))
        categories.add(Category("Music", R.drawable.music))
        categories.add(Category("Nature", R.drawable.nature))
        categories.add(Category("Diet", R.drawable.diet))
        categories.add(Category("Travel", R.drawable.travel))
        categories.add(Category("Crystals", R.drawable.crystals))
        categories.add(Category("Oils", R.drawable.essentialoils))
        categories.add(Category("Chakras", R.drawable.chakras))
        categories.add(Category("Yoga", R.drawable.yoga))
    }

    fun getCategoryWebView(category: Category): String {
        return when (category.title) {
            "Meditation" -> getApplication<Application>().resources.getString(R.string.meditation_data)
            "Journal" -> getApplication<Application>().resources.getString(R.string.journal_data)
            "Love" -> getApplication<Application>().resources.getString(R.string.love_data)
            "Music" -> getApplication<Application>().resources.getString(R.string.music_data)
            "Nature" -> getApplication<Application>().resources.getString(R.string.nature_data)
            "Diet" -> getApplication<Application>().resources.getString(R.string.diet_data)
            "Travel" -> getApplication<Application>().resources.getString(R.string.travel_data)
            "Crystals" -> getApplication<Application>().resources.getString(R.string.crystals_data)
            "Oils" -> getApplication<Application>().resources.getString(R.string.oils_data)
            "Chakras" -> getApplication<Application>().resources.getString(R.string.chakras_data)
            "Yoga" -> getApplication<Application>().resources.getString(R.string.chakras_data)
            else -> ""
        }
    }
}