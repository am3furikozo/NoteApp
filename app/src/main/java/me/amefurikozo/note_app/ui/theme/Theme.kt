/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
  primary = Color.White,
  background = DarkGray,
  onBackground = Color.White,
  surface = LightBlue,
  onSurface = DarkGray
)

@Suppress("FunctionName")
@Composable
fun NoteAppTheme(darkTheme: Boolean = true, content: @Composable () -> Unit) {
  MaterialTheme(
    colors = DarkColorPalette,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}