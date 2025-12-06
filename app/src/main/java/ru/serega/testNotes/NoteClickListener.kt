package ru.serega.testNotes

import android.view.View

interface NoteClickListener {
    fun onEditClick(note: NoteModel)
    fun onStatusClick(note: NoteModel, view: View)
    fun onTextClick(note: NoteModel)

}