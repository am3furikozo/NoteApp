/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.data.repository

import kotlinx.coroutines.flow.Flow
import me.amefurikozo.note_app.feature.note.data.source.NoteDao
import me.amefurikozo.note_app.feature.note.domain.model.Note
import me.amefurikozo.note_app.feature.note.domain.repository.NoteRepository

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
  override fun getAll(): Flow<List<Note>> {
    return dao.getAll()
  }

  override suspend fun getById(id: Int): Note? {
    return dao.getById(id)
  }

  override suspend fun insert(note: Note) {
    dao.insert(note)
  }

  override suspend fun delete(note: Note) {
    dao.delete(note)
  }
}