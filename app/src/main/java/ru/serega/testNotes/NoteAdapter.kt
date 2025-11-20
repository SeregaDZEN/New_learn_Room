package ru.serega.testNotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter() :
    RecyclerView.Adapter<NoteAdapter.NotesHolder>() {
    var data = listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override

    fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesHolder = NotesHolder.inflateFrom(parent)


    override fun onBindViewHolder(
        holder: NotesHolder,
        position: Int
    ) {
        val note = data[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = data.size

    class NotesHolder(item: View) : RecyclerView.ViewHolder(item) {
        val textHolder = item.findViewById<TextView>(R.id.text_note)

        val imageHolder = item.findViewById<ImageView>(R.id.img_status)
        val imageButtonHolder = item.findViewById<ImageButton>(R.id.button_note)


        fun bind(item: Note) {
            textHolder.text = item.text
            textHolder.setOnClickListener {
                imageHolder.isSelected = !imageHolder.isSelected
            }

            imageHolder.setImageResource(item.iconRes)
            imageButtonHolder.setOnClickListener {

            }
        }


        companion object {
            fun inflateFrom(parent: ViewGroup): NotesHolder {
                val layoutInflate = LayoutInflater.from(parent.context)
                val view = layoutInflate.inflate(R.layout.item_note, parent, false)
                return NotesHolder(view)

            }
        }

    }


}