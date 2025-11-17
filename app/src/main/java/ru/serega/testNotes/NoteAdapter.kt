package ru.serega.testNotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val listNotes: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NotesHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesHolder(view)
    }

    override fun onBindViewHolder(
        holder: NotesHolder,
        position: Int
    ) {
        val note = listNotes[position]
        holder.textHolder.text = note.text
        holder.imageHolder.setImageResource(note.iconRes)
        holder.imageButtonHolder.setOnClickListener {

        }

    }

    override fun getItemCount(): Int = listNotes.size


    class NotesHolder(item: View) : RecyclerView.ViewHolder(item) {
        val textHolder = item.findViewById<TextView>(R.id.text_note)
        val imageButtonHolder = item.findViewById<ImageButton>(R.id.button_note)
        val imageHolder = item.findViewById<ImageView>(R.id.img_status)

    }
}