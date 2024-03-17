package com.jeisundev.permissions.feature.multiple

data class MultiplePermissionsState(
    val permissions: List<String> = listOf(android.Manifest.permission.CAMERA)
)
