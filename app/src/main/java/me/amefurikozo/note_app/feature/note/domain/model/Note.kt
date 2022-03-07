/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.amefurikozo.note_app.ui.theme.*

@Entity(tableName = "notes")
data class Note(
  @PrimaryKey val id: Int? = null,
  val title: String,
  val content: String,
  val timestamp: Long,
  val color: Int
) {
  companion object {
    val colors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
  }
}
