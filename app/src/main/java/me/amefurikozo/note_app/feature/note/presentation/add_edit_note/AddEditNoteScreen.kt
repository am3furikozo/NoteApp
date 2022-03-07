/*
 * **********************************************************************************************
 * Copyright (c) 2022. 
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.amefurikozo.note_app.R
import me.amefurikozo.note_app.feature.note.domain.model.Note
import me.amefurikozo.note_app.feature.note.presentation.add_edit_note.components.TransparentHintTextField

@Suppress("FunctionName")
@Composable
fun AddEditNoteScreen(navController: NavController, color: Int, viewModel: AddEditNoteViewModel = hiltViewModel()) {
  val title = viewModel.title.value
  val content = viewModel.content.value
  val scaffoldState = rememberScaffoldState()
  val backgroundAnimatable = remember {
    Animatable(Color(if (color != -1) color else viewModel.color.value))
  }
  val scope = rememberCoroutineScope()

  LaunchedEffect(key1 = true) {
    viewModel.eventFlow.collectLatest { event ->
      when (event) {
        is AddEditNoteViewModel.UiEvent.SaveNote -> navController.navigateUp()
        is AddEditNoteViewModel.UiEvent.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(event.message)
      }
    }
  }

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        onClick = { viewModel.onEvent(AddEditNoteEvent.SaveNote) },
        backgroundColor = MaterialTheme.colors.primary
      ) {
        Icon(
          imageVector = Icons.Default.Save,
          contentDescription = stringResource(
            R.string.note_add_edit_note_components_add_edit_note_screen_save_btn_description
          )
        )
      }
    },
    scaffoldState = scaffoldState
  ) {
    Column(modifier = Modifier.fillMaxSize().background(backgroundAnimatable.value).padding(16.dp)) {
      Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Note.colors.forEach { color ->
          val colorInt = color.toArgb()
          Box(
            modifier = Modifier
              .size(50.dp) // TODO: make it depending on screen size
              .shadow(15.dp, CircleShape)
              .clip(CircleShape)
              .background(color)
              .border(
                width = 3.dp,
                color = if (viewModel.color.value == colorInt) Color.Black else Color.Transparent,
                shape = CircleShape
              )
              .clickable {
                scope.launch {
                  backgroundAnimatable.animateTo(
                    targetValue = Color(colorInt),
                    animationSpec = tween(durationMillis = 500)
                  )
                }
                viewModel.onEvent(AddEditNoteEvent.ChangedColor(colorInt))
              }
          )
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
      TransparentHintTextField(
        text = title.text,
        hint = title.hint,
        onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
        onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangedTitleFocus(it)) },
        isHintVisible = title.isHintVisible,
        singleLine = true,
        textStyle = MaterialTheme.typography.h5
      )
      Spacer(modifier = Modifier.height(16.dp))
      TransparentHintTextField(
        modifier = Modifier.fillMaxHeight(),
        text = content.text,
        hint = content.hint,
        onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) },
        onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangedContentFocus(it)) },
        isHintVisible = content.isHintVisible,
        textStyle = MaterialTheme.typography.body1,
      )
    }
  }
}