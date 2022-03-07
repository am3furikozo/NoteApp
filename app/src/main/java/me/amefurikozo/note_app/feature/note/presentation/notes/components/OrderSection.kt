/*
 * **********************************************************************************************
 * Copyright (c) 2022. 
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.feature.note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.amefurikozo.note_app.R
import me.amefurikozo.note_app.feature.note.domain.util.NoteOrder
import me.amefurikozo.note_app.feature.note.domain.util.OrderType

@Suppress("FunctionName")
@Composable
fun OrderSection(
  modifier: Modifier = Modifier,
  order: NoteOrder = NoteOrder.Date(OrderType.Descending),
  onChange: (NoteOrder) -> Unit
) {
  Column(modifier = modifier) {
    Row(modifier = Modifier.fillMaxWidth()) {
      DefaultRadioButton(
        text = stringResource(R.string.note_notes_components_order_section_radio_btn_sort_by_title),
        selected = order is NoteOrder.Title,
        onSelect = { onChange(NoteOrder.Title(order.type)) })
      Spacer(modifier = Modifier.width(8.dp))
      DefaultRadioButton(
        text = stringResource(R.string.note_notes_components_order_section_radio_btn_sort_by_date),
        selected = order is NoteOrder.Date,
        onSelect = { onChange(NoteOrder.Date(order.type)) })
      Spacer(modifier = Modifier.width(8.dp))
      DefaultRadioButton(
        text = stringResource(R.string.note_notes_components_order_section_radio_btn_sort_by_color),
        selected = order is NoteOrder.Color,
        onSelect = { onChange(NoteOrder.Color(order.type)) })
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
      DefaultRadioButton(
        text = stringResource(R.string.note_notes_components_order_section_radio_btn_direction_asc),
        selected = order.type is OrderType.Ascending,
        onSelect = { onChange(order.copy(OrderType.Ascending)) })
      DefaultRadioButton(
        text = stringResource(R.string.note_notes_components_order_section_radio_btn_direction_desc),
        selected = order.type is OrderType.Descending,
        onSelect = { onChange(order.copy(OrderType.Descending)) })
    }
  }
}