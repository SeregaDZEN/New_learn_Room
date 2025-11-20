package ru.serega.testNotes

interface NotesRepository {
  fun getNotes(): List<Note>
  fun addNote(text: String,iconRes: Int)
  fun deleteNote(id: Int)
  fun updateNote(note: Note)


}


