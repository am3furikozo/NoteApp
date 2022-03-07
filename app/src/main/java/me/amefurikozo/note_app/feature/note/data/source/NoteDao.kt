/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.amefurikozo.note_app.feature.note.domain.model.Note

@Dao
interface NoteDao {
  @Query("SELECT * FROM notes")
  fun getAll(): Flow<List<Note>>

  @Query("SELECT * FROM notes WHERE id = :id")
  suspend fun getById(id: Int): Note?

  @Insert(onConflict = REPLACE)
  suspend fun insert(note: Note)

  @Delete
  suspend fun delete(note: Note)
}