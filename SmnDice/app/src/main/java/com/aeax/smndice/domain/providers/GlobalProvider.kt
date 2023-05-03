package com.aeax.smndice.domain.providers

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.aeax.smndice.domain.repositories.ApiRepository

data class GlobalProvider (
    val navController: NavHostController,
//    val apiRepository: ApiRepository
)

val LocalGlobalProvider = compositionLocalOf<GlobalProvider> {
    error("No GlobalProvider provided")
}