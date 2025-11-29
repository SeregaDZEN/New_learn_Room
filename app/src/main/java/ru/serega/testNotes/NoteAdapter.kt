package ru.serega.testNotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter

class NoteAdapter(
    private val onEditClick: (NoteModel) -> Unit,

) : ListAdapter<NoteModel, NoteAdapter.NotesHolder>(NoteDiffItemCallback()) {


    override

    fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesHolder = NotesHolder.inflateFrom(parent, onEditClick)


    override fun onBindViewHolder(
        holder: NotesHolder,
        position: Int
    ) {
        val note = getItem(position)
        holder.bind(note)
    }

    class NotesHolder(
        item: View,
        private val onEditClick: (NoteModel) -> Unit,

        ) : RecyclerView.ViewHolder(item) {
        private val textNote = item.findViewById<TextView>(R.id.text_note)
        private val imgStatus = item.findViewById<ImageView>(R.id.img_status)
        private val btnEdit = item.findViewById<ImageButton>(R.id.button_change)


        fun bind(note: NoteModel) {
            textNote.text = note.text

            imgStatus.isSelected = note.isDone

            textNote.setOnClickListener {
                note.isDone = !note.isDone
                imgStatus.isSelected = note.isDone
            }
            btnEdit.setOnClickListener {
                onEditClick(note)
            }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup, onEditClick: (NoteModel) -> Unit): NotesHolder {
                val layoutInflate = LayoutInflater.from(parent.context)
                val view = layoutInflate.inflate(R.layout.item_note, parent, false)
                return NotesHolder(view, onEditClick)

            }
        }

    }
}