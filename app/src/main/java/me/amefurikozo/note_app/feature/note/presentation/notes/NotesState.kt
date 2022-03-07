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
import me.amefurikozo.note_app.feature.note.domain.util.OrderType

data class NotesState(
  val notes: List<Note> = emptyList(),
  val order: NoteOrder = NoteOrder.Date(OrderType.Descending),
  val isOrderSectionVisible: Boolean = false
)
