/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.domain.service

import android.app.Application
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.amefurikozo.note_app.R
import me.amefurikozo.note_app.feature.note.domain.model.InvalidNoteException
import me.amefurikozo.note_app.feature.note.domain.model.Note
import me.amefurikozo.note_app.feature.note.domain.repository.NoteRepository
import me.amefurikozo.note_app.feature.note.domain.util.NoteOrder
import me.amefurikozo.note_app.feature.note.domain.util.OrderType

class NoteServiceImpl(
  private val app: Application,
  private val noteRepository: NoteRepository
) : NoteService {
  @Throws(InvalidNoteException::class)
  override suspend fun add(note: Note) {
    when {
      note.title.isBlank() -> throw InvalidNoteException(
        app.getString(
          R.string.note_notes_service_add_exception_empty_title_exception_message
        )
      )
      note.content.isBlank() -> throw InvalidNoteException(
        app.getString(
          R.string.note_notes_service_add_exception_empty_content_exception_message
        )
      )
    }
    noteRepository.insert(note)
  }

  override suspend fun delete(note: Note) {
    noteRepository.delete(note)
  }

  override suspend fun getById(id: Int): Note? {
    return noteRepository.getById(id)
  }

  override fun getAll(order: NoteOrder): Flow<List<Note>> {
    return noteRepository.getAll().map { notes ->
      when (order.type) {
        is OrderType.Ascending -> {
          when (order) {
            is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
            is NoteOrder.Date -> notes.sortedBy { it.timestamp }
            is NoteOrder.Color -> notes.sortedBy { it.color }
          }
        }
        is OrderType.Descending -> {
          when (order) {
            is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
            is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
            is NoteOrder.Color -> notes.sortedByDescending { it.color }
          }
        }
      }
    }
  }
}