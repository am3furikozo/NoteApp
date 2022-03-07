/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.notes

import me.amefurikozo.note_app.feature.note.domain.model.Note
import me.amefurikozo.note_app.feature.note.domain.util.NoteOrder

sealed class NotesEvent {
  data class Order(val order: NoteOrder) : NotesEvent()
  data class Delete(val note: Note) : NotesEvent()
  object Restore : NotesEvent()
  object ToggleOrderSection : NotesEvent()
}
