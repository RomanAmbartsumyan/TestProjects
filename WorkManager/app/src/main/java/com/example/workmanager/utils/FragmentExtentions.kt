package com.example.workmanager.utils

import android.os.Build

fun haveQ(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}