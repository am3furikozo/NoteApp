/*
 * **********************************************************************************************
 * Copyright (c) 2022. 
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.add_edit_note

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.amefurikozo.note_app.R
import me.amefurikozo.note_app.common.presentation.BaseViewModel
import me.amefurikozo.note_app.feature.note.domain.model.InvalidNoteException
import me.amefurikozo.note_app.feature.note.domain.model.Note
import me.amefurikozo.note_app.feature.note.domain.service.NoteService
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
  app: Application,
  private val noteService: NoteService,
  savedStateHandle: SavedStateHandle
) : BaseViewModel(app) {
  sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    object SaveNote : UiEvent()
  }

  private val _title = mutableStateOf(
    NoteTextFieldState(
      hint = context.getString(R.string.note_add_edit_note_title_hint)
    )
  )
  private val _content = mutableStateOf(
    NoteTextFieldState(
      hint = context.getString(R.string.note_add_edit_note_content_hint)
    )
  )
  private val _color = mutableStateOf(Note.colors.random().toArgb())
  private val _eventFlow = MutableSharedFlow<UiEvent>()
  private var noteId: Int? = null
  val title: State<NoteTextFieldState> = _title
  val content: State<NoteTextFieldState> = _content
  val color: State<Int> = _color
  val eventFlow = _eventFlow.asSharedFlow()

  fun onEvent(event: AddEditNoteEvent) {
    when (event) {
      is AddEditNoteEvent.EnteredTitle -> _title.value = title.value.copy(text = event.value)
      is AddEditNoteEvent.ChangedTitleFocus -> {
        _title.value = title.value.copy(isHintVisible = !event.focusState.isFocused && title.value.text.isBlank())
      }
      is AddEditNoteEvent.EnteredContent -> _content.value = content.value.copy(text = event.value)
      is AddEditNoteEvent.ChangedContentFocus -> {
        _content.value = content.value.copy(isHintVisible = !event.focusState.isFocused && content.value.text.isBlank())
      }
      is AddEditNoteEvent.ChangedColor -> _color.value = event.color
      is AddEditNoteEvent.SaveNote -> {
        viewModelScope.launch {
          try {
            noteService.add(
              Note(
                title = title.value.text,
                content = content.value.text,
                timestamp = System.currentTimeMillis(),
                color = color.value,
                id = noteId
              )
            )
            _eventFlow.emit(UiEvent.SaveNote)
          } catch (e: InvalidNoteException) {
            _eventFlow.emit(
              UiEvent.ShowSnackBar(
                message = e.message
                  ?: context.getString(R.string.note_add_edit_note_invalid_note_exception_message_fallback)
              )
            )
          }
        }
      }
    }
  }

  init {
    savedStateHandle.get<Int>("id")?.let { id ->
      if (id != -1) {
        viewModelScope.launch {
          noteService.getById(id)?.also { note ->
            noteId = note.id
            _title.value = title.value.copy(text = note.title, isHintVisible = false)
            _content.value = content.value.copy(text = note.content, isHintVisible = false)
            _color.value = note.color
          }
        }
      }
    }
  }
}