package com.ptk.oldphonepad.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ptk.oldphonepad.ui.ui_states.OldPhonePadScreenUIState
import com.ptk.oldphonepad.util.AppMode
import com.ptk.oldphonepad.viewmodel.OldPhonePadViewModel

@Composable
fun OldPhonePadScreen(viewModel: OldPhonePadViewModel = hiltViewModel()) {
    val uiState by viewModel.uiStates.collectAsState()
    OldPhonePadScreenContent(viewModel, uiState)
}

@Composable
fun OldPhonePadScreenContent(viewModel: OldPhonePadViewModel, uiState: OldPhonePadScreenUIState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()) // Add vertical scroll here

    ) {
        Title()
        Spacer(modifier = Modifier.height(32.dp))
        ModeSwitch(viewModel, uiState)
        when (uiState.currentMode) {
            AppMode.Default -> DefaultModeScreen(viewModel, uiState)
            AppMode.LivePreview -> LivePreviewModeScreen(viewModel, uiState)
        }
    }
}

@Composable
fun Title() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.primary,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Old Phone Pad",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun ModeSwitch(viewModel: OldPhonePadViewModel, uiState: OldPhonePadScreenUIState) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Default",
                modifier = Modifier.padding(end = 16.dp),
                fontWeight = FontWeight.Bold,
                color = if (uiState.currentMode == AppMode.Default) MaterialTheme.colorScheme.primary else Color.Black
            )
            Switch(
                checked = uiState.currentMode == AppMode.LivePreview,
                onCheckedChange = { isChecked ->
                    viewModel.changeMode(if (isChecked) AppMode.LivePreview else AppMode.Default)
                },
            )
            Text(
                text = "Live Preview",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold,
                color = if (uiState.currentMode == AppMode.LivePreview) MaterialTheme.colorScheme.primary else Color.Black
            )
        }
    }
}






