/*
 * **********************************************************************************************
 * Copyright (c) 2022. 
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.notes.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("FunctionName")
@Composable
fun DefaultRadioButton(text: String, selected: Boolean, onSelect: () -> Unit, modifier: Modifier = Modifier) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    RadioButton(
      selected = selected,
      onClick = onSelect,
      colors = RadioButtonDefaults.colors(
        selectedColor = MaterialTheme.colors.primary,
        unselectedColor = MaterialTheme.colors.onBackground
      )
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = text, style = MaterialTheme.typography.body1)
  }
}