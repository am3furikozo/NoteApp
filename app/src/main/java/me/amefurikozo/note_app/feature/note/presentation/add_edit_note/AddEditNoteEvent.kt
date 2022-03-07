/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
  data class EnteredTitle(val value: String) : AddEditNoteEvent()
  data class ChangedTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
  data class EnteredContent(val value: String) : AddEditNoteEvent()
  data class ChangedContentFocus(val focusState: FocusState) : AddEditNoteEvent()
  data class ChangedColor(val color: Int) : AddEditNoteEvent()
  object SaveNote : AddEditNoteEvent()
}
