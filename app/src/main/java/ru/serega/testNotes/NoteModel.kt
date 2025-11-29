package ru.serega.testNotes

data class NoteModel(
    val id: Int,
    val text: String,
    val iconRes: Int = 0,
    var isDone: Boolean = false
)
