package com.ptk.oldphonepad.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ptk.oldphonepad.viewmodel.OldPhonePadViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeypadInputTextField(
    viewModel: OldPhonePadViewModel, inputText: String, onInputTextChange: (String) -> Unit
) {
    val context = LocalContext.current
    TextField(value = inputText,
        onValueChange = onInputTextChange,
        enabled = false,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(32.dp),
        textStyle = LocalTextStyle.current.copy(
            fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold
        ),
        placeholder = {
            Text(
                "Pressed below keys", fontSize = 20.sp, color = Color.White
            )
        },
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Send,
                contentDescription = "Send Message",
                tint = Color.White,
                modifier = Modifier.clickable { viewModel.sendMessage(context) })
        })
}


