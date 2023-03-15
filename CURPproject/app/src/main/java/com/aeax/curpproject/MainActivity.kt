package com.aeax.curpproject

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.aeax.curpproject.ui.register.ui.RegisterScreen
import com.aeax.curpproject.ui.register.ui.RegisterViewModel
import com.aeax.curpproject.ui.theme.CURPprojectTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen(RegisterViewModel())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CURPprojectTheme {
        RegisterScreen(RegisterViewModel())
    }
}

