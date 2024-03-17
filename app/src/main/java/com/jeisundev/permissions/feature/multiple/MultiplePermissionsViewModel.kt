package com.jeisundev.permissions.feature.multiple

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MultiplePermissionsViewModel : ViewModel() {

    private val _state = MutableStateFlow(MultiplePermissionsState())
    val state get() = _state.asStateFlow()

}