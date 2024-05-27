package com.ptk.oldphonepad.util

/**
 * Author: Thomas
 * Date: 27th May 2024
 *
 * This function converts input keys into an output string based on an old phone keypad.
 *
 * @param inputString Input string containing numeric keys, spaces, '*', and '#'.
 * @return Resulting string translated from input keys.
 *
 * Description:
 * 1. Define the keypad using a two-dimensional array.
 * 2. Initialize variables: resultStr for storing the output string, currentKey to track the current key pressed,
 *    and currentCount to count the number of times the currentKey is pressed consecutively.
 *
 * Conditions:
 * (i) If the character is a digit:
 *     - Check if it is the same as currentKey.
 *     - If yes, increment currentCount.
 *     - If no, append the current character using appendCurrentCharacter() method,
 *       then update currentKey and currentCount.
 *
 * (ii) If the character is '#':
 *      - Append the current character using appendCurrentCharacter() method and terminate the loop.
 *        '#' denotes the end of input.
 *
 * (iii) If the character is ' ' (space):
 *       - Append the current character using appendCurrentCharacter() method.
 *       - Reset currentKey and currentCount as spaces indicate a pause.
 *
 * (iv) If the character is '*':
 *      - Append the current character using appendCurrentCharacter() method.
 *      - Delete the last character of resultStr to simulate backspace.
 *      - Reset currentKey and currentCount.
 */

fun getOutputStr(inputString: String): String {
    val resultStr = StringBuilder()
    var currentKey: Char? = null
    var currentCount = 0


    fun appendCurrentCharacter() {
        currentKey?.let { key ->
            val number = key.toString().toInt()
            if (number in 2..9) {
                val index = (currentCount - 1) % keypad[number].size
                resultStr.append(keypad[number][index])
            }
        }
    }

    for (char in inputString) {
        when {
            char.isDigit() -> {
                if (char == currentKey) {
                    currentCount++
                } else {
                    appendCurrentCharacter()
                    currentKey = char
                    currentCount = 1
                }
            }

            char == '#' -> {
                appendCurrentCharacter()
                break
            }

            char == ' ' -> {
                appendCurrentCharacter()
                currentKey = null
                currentCount = 0
            }

            char == '*' -> {
                appendCurrentCharacter()
                if (resultStr.isNotEmpty()) {
                    resultStr.deleteCharAt(resultStr.length - 1)
                }
                currentKey = null
                currentCount = 0
            }
        }
    }
    return resultStr.toString()
}
