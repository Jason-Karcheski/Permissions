package com.jeisundev.permissions.feature.multiple

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jeisundev.permissions.domain.PermissionHandler

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermissionsScreen(permissionHandler: PermissionHandler = PermissionHandler()) {

    val permissionState = rememberMultiplePermissionsState(permissions = permissionHandler.permissions)

    if (permissionState.allPermissionsGranted) {
        Text(text = "All permissions are granted")
    } else {
        Column {
            // Rationale message derived from MultiplePermissionState
            Text(text = permissionHandler.getRationale(
                permissionState.revokedPermissions,
                permissionState.shouldShowRationale
            ))

            Button(
                content = { Text(text = "Request permission") },
                onClick = { permissionHandler.requestPermission(permissionState) }
            )
        }
    }

}