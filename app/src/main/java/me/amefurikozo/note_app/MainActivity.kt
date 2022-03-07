/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import me.amefurikozo.note_app.feature.note.presentation.add_edit_note.AddEditNoteScreen
import me.amefurikozo.note_app.feature.note.presentation.notes.NotesScreen
import me.amefurikozo.note_app.ui.theme.NoteAppTheme
import me.amefurikozo.note_app.util.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      NoteAppTheme {
        Surface(color = MaterialTheme.colors.background) {
          val navController = rememberNavController()
          NavHost(navController = navController, startDestination = Screen.NotesScreen.route) {
            composable(route = Screen.NotesScreen.route) {
              NotesScreen(navController = navController)
            }
            composable(
              route = Screen.AddEditNoteScreen.route + "?id={id}&color={color}",
              arguments = listOf(
                navArgument(name = "id") {
                  type = NavType.IntType
                  defaultValue = -1
                },
                navArgument(name = "color") {
                  type = NavType.IntType
                  defaultValue = -1
                }
              )) {
              AddEditNoteScreen(navController = navController, color = it.arguments?.getInt("color") ?: -1)
            }
          }
        }
      }
    }
  }
}
