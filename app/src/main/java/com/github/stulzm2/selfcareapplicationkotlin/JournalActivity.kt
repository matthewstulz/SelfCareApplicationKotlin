package com.github.stulzm2.selfcareapplicationkotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.stulzm2.selfcareapplicationkotlin.adapter.JournalAdapter
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal
import com.github.stulzm2.selfcareapplicationkotlin.viewmodel.JournalViewModel
import kotlinx.android.synthetic.main.activity_journal.*
import java.util.*
import androidx.recyclerview.widget.ItemTouchHelper



class JournalActivity : AppCompatActivity() {

    private lateinit var journalViewModel: JournalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)
        setSupportActionBar(toolbar_journal)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_journal)
        val adapter = JournalAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        journalViewModel = ViewModelProviders.of(this).get(JournalViewModel::class.java)
        journalViewModel.allJournals.observe(this, Observer { journals ->
            journals?.let { adapter.setJournals(it) }
        })

        val helper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val journal = adapter.getJournalAtPosition(position)
                    Toast.makeText(this@JournalActivity, R.string.delete_journal, Toast.LENGTH_SHORT).show()

                    // Delete the word
                    journalViewModel.delete(journal)
                }
            })

        helper.attachToRecyclerView(recyclerView)

        adapter.setOnItemClickListener(object : JournalAdapter.JournalAdapterOnItemClickHandler {
            override fun onItemClick(journal: Journal) {
                launchUpdateJournalActivity(journal)
            }
        })
    }

    companion object {
        const val newJournalActivityRequestCode = 1
        const val editJournalActivityRequestCode = 2
        const val EXTRA_DATA_UPDATE_JOURNAL = "com.github.stulzm2.selfcareapplicationkotlin.EXTRA_DATA_UPDATE_JOURNAL"
        const val EXTRA_DATA_ID = "com.github.stulzm2.selfcareapplicationkotlin.EXTRA_DATA_ID"
    }

    fun launchUpdateJournalActivity(journal: Journal) {
        val intent = Intent(this, AddEditJournalActivity::class.java)
        intent.putExtra(EXTRA_DATA_ID, journal.id)
        intent.putExtra(EXTRA_DATA_UPDATE_JOURNAL, journal.entry)
        startActivityForResult(intent, editJournalActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val date = Calendar.getInstance().time

        if (requestCode == newJournalActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val journal = Journal(0, it.getStringExtra(AddEditJournalActivity.EXTRA_REPLY), date)
                journalViewModel.insert(journal)
                Toast.makeText(applicationContext, R.string.journal_saved, Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == newJournalActivityRequestCode && resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(applicationContext, R.string.journal_empty, Toast.LENGTH_SHORT).show()
        } else if (requestCode == editJournalActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val id = it.getIntExtra(AddEditJournalActivity.EXTRA_REPLY_ID, -1)
                val entry = it.getStringExtra(AddEditJournalActivity.EXTRA_REPLY)

                if (id != -1) {
                    val journal = Journal(id, entry, date)
                    journalViewModel.update(journal)
                    Toast.makeText(applicationContext, R.string.journal_updated, Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(applicationContext, R.string.journal_not_updated, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialog() {
        val fragmentManager = supportFragmentManager
        val newFragment = ResourceCustomDialog()
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_journal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_new_journal -> {
                val intent = Intent(this, AddEditJournalActivity::class.java)
                startActivityForResult(intent, newJournalActivityRequestCode)
                true
            }
            R.id.action_clear_data -> {
                journalViewModel.deleteAll()
                true
            }
            R.id.action_resources -> {
                showDialog()
                true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
