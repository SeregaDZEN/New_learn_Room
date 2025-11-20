package ru.serega.testNotes

object NotesData : NotesRepository {
    private val _notes = mutableListOf<Note>()

    private var nextId = 0
    override fun getNotes(): List<Note> = _notes


    override fun addNote(text: String, iconRes: Int) {
        val note = Note(nextId++, text, iconRes, false)
        _notes.add(note)
    }

    override fun deleteNote(id: Int) {
        _notes.removeIf { it.id == id }
    }

    override fun updateNote(note: Note) {
        val index = _notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            _notes[index] = note
        }


    }
}