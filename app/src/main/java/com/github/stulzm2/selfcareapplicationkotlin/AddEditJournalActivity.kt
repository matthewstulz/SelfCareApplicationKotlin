package com.github.stulzm2.selfcareapplicationkotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_edit_journal.*

class AddEditJournalActivity : AppCompatActivity() {

    private lateinit var editTextJournalEntry: EditText
    private var extras: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_journal)
        setSupportActionBar(toolbar_add_edit_journal)

        editTextJournalEntry = findViewById(R.id.edit_text_add_edit_journal)
        extras = intent.extras

        if (extras != null) {
            val journalEntry = extras!!.getString(JournalActivity.EXTRA_DATA_UPDATE_JOURNAL, "")
            if (journalEntry.isNotEmpty()) {
                supportActionBar?.setTitle(R.string.edit_journal)
                editTextJournalEntry.setText(journalEntry)
            }
        } else {
            supportActionBar?.setTitle(R.string.new_journal)
        }

        editTextJournalEntry.requestFocus()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun saveJournalEntry() {
        val replyIntent = Intent()
        if (TextUtils.isEmpty(editTextJournalEntry.text)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            val entry = editTextJournalEntry.text.toString()
            replyIntent.putExtra(EXTRA_REPLY, entry)
            if (extras != null && extras!!.containsKey(JournalActivity.EXTRA_DATA_ID)) {
                val id = extras!!.getInt(JournalActivity.EXTRA_DATA_ID, -1)
                if (id != -1) {
                    replyIntent.putExtra(EXTRA_REPLY_ID, id)
                }
            }
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
    }

    companion object {
        const val EXTRA_REPLY = "com.github.stulzm2.selfcareapplicationkotlin.EXTRA_REPLY"
        const val EXTRA_REPLY_ID = "com.github.stulzm2.selfcareapplicationkotlin.EXTRA_REPLY_ID"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.add_edit_journal_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_journal_save -> {
                saveJournalEntry()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
