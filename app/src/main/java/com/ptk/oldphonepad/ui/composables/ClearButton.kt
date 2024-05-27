package com.ptk.oldphonepad.ui.composables

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

@Composable
fun ColumnScope.ClearButton(onClick: () -> Unit) {
    TextButton(
        modifier = Modifier.align(Alignment.End), onClick = onClick
    ) {
        Text(
            text = "Clear", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Red
        )
    }
}