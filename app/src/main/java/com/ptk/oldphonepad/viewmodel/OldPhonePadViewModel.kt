package com.ptk.oldphonepad.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptk.oldphonepad.ui.ui_states.OldPhonePadScreenUIState
import com.ptk.oldphonepad.util.AppMode
import com.ptk.oldphonepad.util.getOutputStr
import com.ptk.oldphonepad.util.keypad
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OldPhonePadViewModel @Inject constructor() : ViewModel() {

    private val _uiStates = MutableStateFlow(OldPhonePadScreenUIState())
    val uiStates = _uiStates.asStateFlow()

    private var currentKey: Char? = null
    private var currentCount = 0
    private var job: Job? = null

    fun updateInputStr(context: Context, inputStr: Char) {
        if (_uiStates.value.inputStr.contains("#")) {
            Toast.makeText(
                context,
                "Please clear by pressing clear button to clear the existing message to send new message",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val updatedText = if (inputStr == '0') {
                _uiStates.value.inputStr + " "
            } else {
                _uiStates.value.inputStr + inputStr
            }
            _uiStates.update { it.copy(inputStr = updatedText) }
        }
    }

    fun clearInput() {
        _uiStates.update { it.copy(inputStr = "", outputStr = "", liveOutputResult = "") }
        resetCurrent()
    }

    fun sendMessage(context: Context) {
        if (_uiStates.value.inputStr.contains("#")) {
            Toast.makeText(
                context,
                "Please clear by pressing clear button to clear the existing message to send new message",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            updateInputStr(context, '#')
            val outputText = getOutputStr(_uiStates.value.inputStr)
            _uiStates.update { it.copy(outputStr = outputText) }
        }
    }

    fun changeMode(mode: AppMode) {
        _uiStates.update {
            it.copy(
                currentMode = mode, inputStr = "", outputStr = "", liveOutputResult = ""
            )
        }
        resetCurrent()
    }

    fun getLiveUpdates(key: Char) {
        job?.cancel()
        when {
            key.isDigit() -> handleDigitKey(key)
            key == '*' -> handleBackspaceKey()
            key == '0' -> handleSpaceKey()
        }
    }

    private fun handleDigitKey(key: Char) {
        if (key == currentKey) {
            currentCount++
        } else {
            appendCurrentCharacter()
            currentKey = key
            currentCount = 1
        }
        startDelay()
    }

    private fun handleBackspaceKey() {
        _uiStates.update {
            it.copy(liveOutputResult = it.liveOutputResult.dropLast(1))
        }
        resetCurrent()
    }

    private fun handleSpaceKey() {
        appendCurrentCharacter()
        resetCurrent()
    }

    private fun startDelay() {
        job = viewModelScope.launch {
            delay(300)
            appendCurrentCharacter()
            resetCurrent()
        }
    }

    private fun appendCurrentCharacter() {
        currentKey?.let { key ->
            val number = key.toString().toInt()
            if (number == 0) {
                _uiStates.update { it.copy(liveOutputResult = it.liveOutputResult + " ") }
            } else {
                if (number in 1..9) {
                    val index = (currentCount - 1) % keypad[number].size
                    _uiStates.update {
                        it.copy(liveOutputResult = it.liveOutputResult + keypad[number][index])
                    }
                }
            }
        }
    }

    private fun resetCurrent() {
        currentKey = null
        currentCount = 0
    }
}
