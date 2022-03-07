/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import me.amefurikozo.note_app.feature.note.domain.model.Note

@Database(
  entities = [Note::class],
  version = 1
)
abstract class NoteDatabase : RoomDatabase() {
  companion object {
    const val DATABASE_NAME = "notes_db"
  }

  abstract val noteDao: NoteDao
}