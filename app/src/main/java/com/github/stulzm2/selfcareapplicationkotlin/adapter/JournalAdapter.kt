package com.github.stulzm2.selfcareapplicationkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.stulzm2.selfcareapplicationkotlin.R
import com.github.stulzm2.selfcareapplicationkotlin.database.DateConverter
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal



class JournalAdapter internal constructor(context: Context
) : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var journals = emptyList<Journal>()
    private lateinit var journalClickListener: JournalAdapterOnItemClickHandler


    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View) {
            journalClickListener.onItemClick(journals[adapterPosition])
        }

        private var journalEntry: TextView? = null
        private var journalDate: TextView? = null

        init {
            journalEntry = itemView.findViewById(R.id.text_view_journal_entry)
            journalDate = itemView.findViewById(R.id.text_view_journal_date)
        }

        fun bind(journal: Journal) {
            journalEntry?.text = journal.entry
            journalDate?.text = DateConverter.getDateFormat().format(journal.date)
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val itemView = inflater.inflate(R.layout.card_view_journal_item, parent, false)
        return JournalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val journal: Journal = journals[position]
        holder.bind(journal)
//        holder.journalDateItemView.text = DateConverter.getDateFormat().format(journal.date)
//        holder.journalEntryItemView.text = journal.entry

        // Test to see if autoIncrement on id's were working
//        val id = current.id
//        holder.itemView.setOnClickListener { view ->
//            Toast.makeText(view.context, id.toString(), Toast.LENGTH_LONG).show()
//        }
    }

    internal fun setJournals(journals: List<Journal>) {
        this.journals = journals
        notifyDataSetChanged()
    }

    override fun getItemCount() = journals.size

    fun setOnItemClickListener(clickListener: JournalAdapterOnItemClickHandler) {
        journalClickListener = clickListener
    }

    interface JournalAdapterOnItemClickHandler {
        fun onItemClick(journal: Journal)
    }
}