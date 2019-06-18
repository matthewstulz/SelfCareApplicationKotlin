package com.github.stulzm2.selfcareapplicationkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.github.stulzm2.selfcareapplicationkotlin.adapter.CategoryAdapter
import com.github.stulzm2.selfcareapplicationkotlin.model.Category
import com.github.stulzm2.selfcareapplicationkotlin.viewmodel.CategoryViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recycler_view_category
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        recycler_view_category.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            recycler_view_category.hasFixedSize()
            val categoryViewModel = ViewModelProviders.of(this@MainActivity).get(CategoryViewModel::class.java)
            adapter = CategoryAdapter(categoryViewModel.getCategories())
            (adapter as CategoryAdapter).setOnItemClickListener(object : CategoryAdapter.CategoryAdapterOnItemClickHandler {
                override fun onItemClick(category: Category) {
                    categoryClicked(category)
                }
            })
        }
    }

    private fun categoryClicked(category : Category) {
        Toast.makeText(this, "Clicked: ${category.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
