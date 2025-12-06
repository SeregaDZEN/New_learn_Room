package ru.serega.testNotes

import androidx.recyclerview.widget.DiffUtil

class NoteDiffItemCallback : DiffUtil.ItemCallback<NoteModel>() {

    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel) = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel) = (oldItem == newItem)
}