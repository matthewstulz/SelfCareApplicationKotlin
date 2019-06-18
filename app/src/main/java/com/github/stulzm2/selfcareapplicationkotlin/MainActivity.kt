package com.github.stulzm2.selfcareapplicationkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.github.stulzm2.selfcareapplicationkotlin.adapter.CategoryAdapter
import com.github.stulzm2.selfcareapplicationkotlin.model.Category

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        recycler_view_category.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = CategoryAdapter(categories = listOf(
                Category("One"),
                Category("Two"),
                Category("Three"),
                Category("Four"),
                Category("Five"),
                Category("Six"),
                Category("Seven"),
                Category("Eight"),
                Category("Nine"),
                Category("Ten")
            ))
        }
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
