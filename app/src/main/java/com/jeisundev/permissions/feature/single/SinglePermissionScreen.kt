package com.jeisundev.permissions.feature.single

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.jeisundev.permissions.domain.PermissionHandler

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermissionScreen(permissionHandler: PermissionHandler = PermissionHandler()) {

    val permissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)

    if (permissionState.status.isGranted) {
        Text(text = "All permissions are granted")
    } else {
        Column {
            // Rationale message derived from PermissionState
            Text(text = permissionHandler.getRationale(permissionState))

            Button(
                content = { Text(text = "Request permission") },
                onClick = { permissionHandler.requestPermission(permissionState) }
            )
        }
    }

}