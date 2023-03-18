package com.aeax.curpproject.ui

sealed class Routes(val path: String) {
    object Loading : Routes("loading")
    object Register : Routes("home")
    object Info : Routes("info")
}