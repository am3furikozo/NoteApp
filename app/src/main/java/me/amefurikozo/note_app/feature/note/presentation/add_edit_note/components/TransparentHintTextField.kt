/*
 * **********************************************************************************************
 * Copyright (c) 2022. 
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Suppress("FunctionName")
@Composable
fun TransparentHintTextField(
  text: String,
  hint: String,
  modifier: Modifier = Modifier,
  isHintVisible: Boolean = true,
  onValueChange: (String) -> Unit,
  textStyle: TextStyle = TextStyle(),
  singleLine: Boolean = false,
  onFocusChange: (FocusState) -> Unit
) {
  Box(modifier = modifier) {
    BasicTextField(
      modifier = Modifier.fillMaxWidth().onFocusChanged(onFocusChange),
      value = text,
      onValueChange = onValueChange,
      singleLine = singleLine,
      textStyle = textStyle
    )
    if (isHintVisible) Text(text = hint, style = textStyle, color = Color.DarkGray)
  }
}