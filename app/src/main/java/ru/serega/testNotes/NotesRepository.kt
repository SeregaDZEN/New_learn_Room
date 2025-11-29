package ru.serega.testNotes

interface NotesRepository {
  fun getNotes(): List<NoteModel>
  fun addNote(text: String,iconRes: Int)
  fun deleteNote(id: Int)
  fun updateNote(note: NoteModel)


}


