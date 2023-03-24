package com.aeax.curpproject

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aeax.curpproject.models.Routes
import com.aeax.curpproject.ui.info.InfoScreen
import com.aeax.curpproject.ui.register.traditionalregister.TraditionalRegisterScreen
import com.aeax.curpproject.ui.register.traditionalregister.TraditionalRegisterViewModel
import com.aeax.curpproject.ui.register.wizardregister.WizardRegisterScreen
import com.aeax.curpproject.ui.register.wizardregister.WizardRegisterViewModel
import com.aeax.curpproject.ui.selectormodo.SelectorModeScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.SelectorMode.path) {
                composable(Routes.SelectorMode.path) {
                    SelectorModeScreen(navController)
                }
                composable(Routes.TraditionalRegister.path) {
                    TraditionalRegisterScreen(TraditionalRegisterViewModel(), navController)
                }
                composable(Routes.WizardRegister.path) {
                     WizardRegisterScreen(WizardRegisterViewModel(), navController)
                }
                composable(Routes.Info.path + "/{info}", arguments = listOf(navArgument("info") { type = NavType.StringType }, navArgument("isError") { type = NavType.BoolType })) {
                    navBackStack -> InfoScreen(navBackStack.arguments?.getString("info") ?: "", navBackStack.arguments?.getBoolean("isError") ?: false, navController)
                }
            }
        }
    }
}