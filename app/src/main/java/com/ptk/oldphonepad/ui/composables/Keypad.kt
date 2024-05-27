package com.ptk.oldphonepad.ui.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ptk.oldphonepad.ui.theme.Purple40
import com.ptk.oldphonepad.viewmodel.OldPhonePadViewModel

@Composable
fun Keypad(
    isLivePreview: Boolean = false,
    viewModel: OldPhonePadViewModel = hiltViewModel()
) {
    Column {
        Row {
            Key(
                label = '1',
                subText = "& ' (",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
            Key(
                label = '2',
                subText = "a b c",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
            Key(
                label = '3',
                subText = "d e f",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
        }
        Row {
            Key(
                label = '4',
                subText = "g h i",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
            Key(
                label = '5',
                subText = "j k l",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
            Key(
                label = '6',
                subText = "m n o",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
        }
        Row {
            Key(
                label = '7',
                subText = "p q r s",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
            Key(
                label = '8',
                subText = "t u v",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
            Key(
                label = '9',
                subText = "w x y z",
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
        }
        Row {
            Key(
                label = '*',
                icon = Icons.AutoMirrored.Filled.Backspace,
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
            Key(
                label = '0',
                icon = Icons.Default.SpaceBar,
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
            Key(
                label = '#',
                icon = Icons.AutoMirrored.Filled.Send,
                isSend = true,
                isLivePreview = isLivePreview,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun RowScope.Key(
    label: Char,
    subText: String? = null,
    icon: ImageVector? = null,
    isSend: Boolean = false,
    isLivePreview: Boolean = false,
    viewModel: OldPhonePadViewModel
) {
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    Button(
        onClick = {
            if (isLivePreview) {
                viewModel.getLiveUpdates(label)
            } else {
                if (isSend) {
                    viewModel.sendMessage(context)
                } else {
                    viewModel.updateInputStr(context, label)
                }
            }
        },
        modifier = Modifier
            .weight(1f)
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Purple40),
        interactionSource = interactionSource
    ) {
        KeyContent(label = label, subText = subText, icon = icon)
    }
}

@Composable
fun KeyContent(
    label: Char,
    subText: String?,
    icon: ImageVector?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "$label", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        if (subText != null) {
            Text(text = subText, fontSize = 14.sp, color = Color.White)
        } else {
            icon?.let {
                Icon(icon, contentDescription = null)
            }
        }
    }
}
