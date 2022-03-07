/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.notes

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.amefurikozo.note_app.common.presentation.BaseViewModel
import me.amefurikozo.note_app.feature.note.domain.model.Note
import me.amefurikozo.note_app.feature.note.domain.service.NoteService
import me.amefurikozo.note_app.feature.note.domain.util.NoteOrder
import me.amefurikozo.note_app.feature.note.domain.util.OrderType
import javax.inject.Inject

@SuppressLint("LongLogTag")
@HiltViewModel
class NotesViewModel @Inject constructor(
  app: Application,
  private val noteService: NoteService
) : BaseViewModel(app) {
  private val _state = mutableStateOf(NotesState())
  private var recentlyDeletedNote: Note? = null
  private var getNotesJob: Job? = null
  val state: State<NotesState> = _state

  private fun getNotes(order: NoteOrder) {
    getNotesJob?.cancel()
    getNotesJob = noteService.getAll(order)
      .onEach { notes -> _state.value = _state.value.copy(notes = notes, order = order) }
      .launchIn(viewModelScope)
  }

  fun onEvent(event: NotesEvent) {
    when (event) {
      is NotesEvent.Order ->
        if (state.value.order::class != event.order::class
          || state.value.order.type != event.order.type
        ) getNotes(event.order)
      is NotesEvent.Delete -> {
        viewModelScope.launch {
          noteService.delete(event.note)
          recentlyDeletedNote = event.note
        }
      }
      is NotesEvent.Restore -> {
        viewModelScope.launch {
          recentlyDeletedNote?.let { noteService.add(it) }
          recentlyDeletedNote = null
        }
      }
      is NotesEvent.ToggleOrderSection -> {
        _state.value = state.value.copy(
          isOrderSectionVisible = !state.value.isOrderSectionVisible
        )
      }
    }
  }

  init {
    getNotes(NoteOrder.Date(OrderType.Descending))
  }
}