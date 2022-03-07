/*
 * **********************************************************************************************
 * Copyright (c) 2022. 
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.notes

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import me.amefurikozo.note_app.R
import me.amefurikozo.note_app.feature.note.presentation.notes.components.NoteItem
import me.amefurikozo.note_app.feature.note.presentation.notes.components.OrderSection
import me.amefurikozo.note_app.util.Screen

@Suppress("FunctionName")
@Composable
fun NotesScreen(navController: NavController, viewModel: NotesViewModel = hiltViewModel()) {
  val state = viewModel.state.value
  val scaffoldState = rememberScaffoldState()
  val scope = rememberCoroutineScope()
  val onDeleteMessage = stringResource(R.string.note_notes_components_notes_screen_on_delete_snack_bar_message)
  val onDeleteActionLabel = stringResource(R.string.note_notes_components_notes_screen_on_delete_snack_bar_action_label)

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        onClick = { navController.navigate(Screen.AddEditNoteScreen.route) },
        backgroundColor = MaterialTheme.colors.primary
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = stringResource(R.string.note_notes_components_notes_screen_add_btn_description)
        )
      }
    },
    scaffoldState = scaffoldState
  ) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = stringResource(R.string.note_notes_components_notes_screen_header_text),
          style = MaterialTheme.typography.h4
        )
        IconButton(onClick = {
          viewModel.onEvent(NotesEvent.ToggleOrderSection)
        }) {
          Icon(
            imageVector = Icons.Default.Sort,
            contentDescription = stringResource(R.string.note_notes_components_notes_screen_sort_btn_description)
          )
        }
      }
      AnimatedVisibility(
        visible = state.isOrderSectionVisible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
      ) {
        OrderSection(
          modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
          order = state.order,
          onChange = { viewModel.onEvent(NotesEvent.Order(it)) }
        )
      }
      Spacer(modifier = Modifier.height(16.dp))
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.notes) { note ->
          NoteItem(
            modifier = Modifier
              .fillMaxWidth()
              .clickable {
                navController.navigate(Screen.AddEditNoteScreen.route + "?id=${note.id}&color=${note.color}")
              },
            note = note,
            onDelete = {
              viewModel.onEvent(NotesEvent.Delete(note))
              scope.launch {
                val result = scaffoldState.snackbarHostState.showSnackbar(
                  message = onDeleteMessage,
                  actionLabel = onDeleteActionLabel
                )
                if (result == SnackbarResult.ActionPerformed) viewModel.onEvent(NotesEvent.Restore)
              }
            })
          Spacer(modifier = Modifier.height(16.dp))
        }
      }
    }
  }
}