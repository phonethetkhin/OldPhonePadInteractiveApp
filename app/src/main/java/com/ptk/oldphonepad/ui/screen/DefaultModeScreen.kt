package com.ptk.oldphonepad.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ptk.oldphonepad.ui.composables.ClearButton
import com.ptk.oldphonepad.ui.composables.CustomHorizontalDivider
import com.ptk.oldphonepad.ui.composables.Keypad
import com.ptk.oldphonepad.ui.composables.KeypadInputTextField
import com.ptk.oldphonepad.ui.composables.ResultTextBox
import com.ptk.oldphonepad.ui.ui_states.OldPhonePadScreenUIState
import com.ptk.oldphonepad.viewmodel.OldPhonePadViewModel

@Composable
fun DefaultModeScreen(viewModel: OldPhonePadViewModel, uiState: OldPhonePadScreenUIState) {
    Column(modifier = Modifier.padding(16.dp)) {

        KeypadInputTextField(viewModel = viewModel,
            inputText = uiState.inputStr,
            onInputTextChange = {})

        ClearButton { viewModel.clearInput() }

        CustomHorizontalDivider()

        ResultTextBox(outputText = uiState.outputStr)

        CustomHorizontalDivider()

        Keypad(isLivePreview = false)
    }
}
