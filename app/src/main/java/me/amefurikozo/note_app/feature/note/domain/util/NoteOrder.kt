/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.domain.util

sealed class NoteOrder(val type: OrderType) {
  class Title(type: OrderType) : NoteOrder(type)
  class Date(type: OrderType) : NoteOrder(type)
  class Color(type: OrderType) : NoteOrder(type)

  fun copy(type: OrderType): NoteOrder {
    return when (this) {
      is Title -> Title(type)
      is Date -> Date(type)
      is Color -> Color(type)
    }
  }
}
