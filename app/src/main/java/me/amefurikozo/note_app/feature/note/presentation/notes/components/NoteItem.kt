/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import me.amefurikozo.note_app.R
import me.amefurikozo.note_app.feature.note.domain.model.Note

@Suppress("FunctionName")
@Composable
fun NoteItem(
  note: Note,
  modifier: Modifier = Modifier,
  cornerRadius: Dp = 10.dp,
  cutCornerSize: Dp = 30.dp,
  onDelete: () -> Unit
) {
  Box(modifier = modifier) {
    Canvas(modifier = Modifier.matchParentSize()) {
      val clipPath = Path().apply {
        lineTo(size.width - cutCornerSize.toPx(), 0f)
        lineTo(size.width, cutCornerSize.toPx())
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
      }
      clipPath(clipPath) {
        drawRoundRect(
          color = Color(note.color),
          size = size,
          cornerRadius = CornerRadius(cornerRadius.toPx())
        )
        drawRoundRect(
          color = Color(
            ColorUtils.blendARGB(note.color, Color.Black.toArgb(), 0.2f)
          ),
          topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
          size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
          cornerRadius = CornerRadius(cornerRadius.toPx())
        )
      }
    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp).padding(end = 32.dp)) {
      Text(
        text = note.title,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onSurface,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = note.content,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface,
        maxLines = 10,
        overflow = TextOverflow.Ellipsis
      )
    }
    IconButton(
      onClick = onDelete,
      modifier = Modifier.align(Alignment.BottomEnd)
    ) {
      Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = stringResource(R.string.note_notes_components_note_item_delete_btn_description)
      )
    }
  }
}