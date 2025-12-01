package ru.serega.testNotes

import android.view.View

interface NoteClickListener {
    fun onEditClick(note: NoteModel)
    fun onTextClick(note: NoteModel, view: View)
}