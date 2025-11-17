package ru.serega.testNotes

data class Note(
    val text: String,
    val iconRes: Int,
    val isDone: Boolean = false
)
