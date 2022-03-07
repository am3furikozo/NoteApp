/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.util

sealed class Screen(val route: String) {
  object AddEditNoteScreen : Screen("add_edit_note_screen")
  object NotesScreen : Screen("notes_screen")
}
