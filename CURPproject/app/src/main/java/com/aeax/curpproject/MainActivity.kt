package com.aeax.curpproject

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aeax.curpproject.ui.Routes
import com.aeax.curpproject.ui.info.ui.InfoScreen
import com.aeax.curpproject.ui.register.ui.RegisterScreen
import com.aeax.curpproject.ui.register.ui.RegisterViewModel
import com.aeax.curpproject.ui.theme.CURPprojectTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.Register.path) {
                composable(Routes.Register.path) {
                    RegisterScreen(RegisterViewModel(), navController)
                }
                composable(Routes.Info.path + "/{info}", arguments = listOf(navArgument("info") { type = NavType.StringType }, navArgument("isError") { type = NavType.BoolType })) {
                    navBackStack -> InfoScreen(navBackStack.arguments?.getString("info") ?: "", navBackStack.arguments?.getBoolean("isError") ?: false, navController)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CURPprojectTheme {
        RegisterScreen(RegisterViewModel(), rememberNavController())
    }
}

