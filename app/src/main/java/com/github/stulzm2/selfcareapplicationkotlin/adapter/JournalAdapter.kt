package com.github.stulzm2.selfcareapplicationkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.stulzm2.selfcareapplicationkotlin.R
import com.github.stulzm2.selfcareapplicationkotlin.database.DateConverter
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal

class JournalAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var journals = emptyList<Journal>()

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val journalDateItemView: TextView = itemView.findViewById(R.id.text_view_journal_date)
        val journalEntryItemView: TextView = itemView.findViewById(R.id.text_view_journal_entry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val itemView = inflater.inflate(R.layout.card_view_journal_item, parent, false)
        return JournalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val current = journals[position]
        holder.journalDateItemView.text = DateConverter.getDateFormat().format(current.date)
        holder.journalEntryItemView.text = current.entry
    }

    internal fun setJournals(journals: List<Journal>) {
        this.journals = journals
        notifyDataSetChanged()
    }

    override fun getItemCount() = journals.size

}