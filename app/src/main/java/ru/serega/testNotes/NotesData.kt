package ru.serega.testNotes

object NotesData : NotesRepository {
    private var nextId = 0
    private var _notes = mutableListOf<NoteModel>()


    override fun currentList(): List<NoteModel> = _notes.toList()

    override fun addNote(text: String, iconRes: Int) {
        val note = NoteModel(nextId++, text, iconRes, false)
        _notes.add(note)
    }


    override fun deleteNote(id: Int) {
        _notes= _notes.filterNot { it.id == id }.toMutableList()
    }

    override fun updateNote(note: NoteModel) {
        val index = _notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            _notes[index] = note
        }
    }
}