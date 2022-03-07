/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.domain.repository

import kotlinx.coroutines.flow.Flow
import me.amefurikozo.note_app.feature.note.domain.model.Note

interface NoteRepository {
  fun getAll(): Flow<List<Note>>

  suspend fun getById(id: Int): Note?

  suspend fun insert(note: Note)

  suspend fun delete(note: Note)
}