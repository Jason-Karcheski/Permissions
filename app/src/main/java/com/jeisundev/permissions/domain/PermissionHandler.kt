package com.jeisundev.permissions.domain

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import java.lang.StringBuilder

/**
 * A class for handling system permissions consistently. Could also be split up into usecases instead of a handler class.
 */
@OptIn(ExperimentalPermissionsApi::class)
class PermissionHandler {

    val permissions = listOf(
        android.Manifest.permission.CAMERA
    )

    /**
     * Get a rationale message to show the user explaining why a  permission is needed.
     */
    fun getRationale(permission: PermissionState) : String =
        "${permission.permission} is needed for this feature. Please grant the permission."

    /**
     * Get a rationale message to show the user explaining why the permissions are needed.
     */
    fun getRationale(
        revokedPermissions: List<PermissionState>,
        shouldShowRationale: Boolean
    ) : String {
        val textToShow = StringBuilder().apply {
            append("The ")
        }

        revokedPermissions.forEachIndexed { index, permissionState ->
            textToShow.append(permissionState.permission)

            val nextToAppend = when {
                revokedPermissions.size > 1 && index == revokedPermissions.size - 2 -> ", and "
                index == revokedPermissions.size - 1 -> " "
                else -> ", "
            }
            textToShow.append(nextToAppend)
        }

        textToShow.apply {
            append(if (permissions.size == 1) "permission is" else "permissions are")
            append(
                if (shouldShowRationale) {
                    " important. Please grant all of them for the app to function properly."
                } else {
                    " denied. The app cannot function without them."
                }
            )
        }

        return textToShow.toString()
    }

    /**
     * Handle single permission request via PermissionState.
     */
    fun requestPermission(permissionState: PermissionState) {
        permissionState.launchPermissionRequest()
    }

    /**
     * Handle multiple permission requests via MultiplePermissionsState.
     */
    fun requestPermission(permissionsState: MultiplePermissionsState) {
        permissionsState.launchMultiplePermissionRequest()
    }

}