package com.valdizz.material2sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valdizz.material2sample.listener.NoteClickListener
import com.valdizz.material2sample.R
import com.valdizz.material2sample.common.Constants
import com.valdizz.material2sample.db.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter internal constructor(context: Context, private val noteClickListener: NoteClickListener) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTextItemView: TextView = itemView.findViewById(R.id.tv_note_text)
        val noteDateItemView: TextView = itemView.findViewById(R.id.tv_note_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.noteTextItemView.text = current.note
        val dateTime = Date(current.date)
        val sdf = SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault())
        holder.noteDateItemView.text = sdf.format(dateTime)
        holder.itemView.setOnClickListener {
            noteClickListener.onClick(current.note, sdf.format(dateTime))
        }
    }

    internal fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount() = notes.size
}