package com.example.workmanager.permissions

import android.Manifest
import android.content.Context
import com.example.workmanager.utils.haveQ
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

object CheckPermissions {
    private val PERMISSIONS = listOfNotNull(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE.takeIf { haveQ().not() }
    )

    fun checkPermission(context: Context): Boolean {
        var result = false
        Dexter.withContext(context)
            .withPermissions(PERMISSIONS)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    result = report.areAllPermissionsGranted()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            })
            .check()
        return result
    }
}