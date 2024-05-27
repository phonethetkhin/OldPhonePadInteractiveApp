package com.ptk.oldphonepad.ui.ui_states

import com.ptk.oldphonepad.util.AppMode

data class OldPhonePadScreenUIState(
    val inputStr: String = "",
    val outputStr: String = "",
    val currentMode: AppMode = AppMode.Default,
    val liveOutputResult: String = ""
)
