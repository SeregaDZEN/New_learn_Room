package ru.serega.testNotes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NoteModel(
    val id: Int,
    val text: String,
    val iconRes: Int = 0,
    var isDone: Boolean = false
) : Parcelable
