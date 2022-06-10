package com.e.codingpractice.dfs

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Links(
    var first: MutableState<Int> = mutableStateOf(0),
    var second: MutableState<Int> = mutableStateOf(0),
    var error : MutableState<Boolean> = mutableStateOf(false)
)