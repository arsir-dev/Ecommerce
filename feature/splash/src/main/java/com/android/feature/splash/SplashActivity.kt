package com.android.feature.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.feature.splash.ui.theme.EcommerceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceTheme {
                val destination by viewModel.splashDestination.collectAsState()

                LaunchedEffect(destination) {
                    when (destination) {
                        SplashDestination.LOGIN -> {
                            Log.e("TAG", "onCreate: $destination", )
                            val intent = Intent(this@SplashActivity, Class.forName("com.android.ecommerce.MainActivity"))
                            startActivity(intent)
                            finish()
                        }
                        SplashDestination.ONBOARDING -> {
                            Log.e("TAG", "onCreate: $destination", )
                            val intent = Intent(this@SplashActivity, Class.forName("com.android.feature.onboarding.OnboardingActivity"))
                            startActivity(intent)
                            finish()
                        }
                        SplashDestination.LOADING -> {
                            // Do nothing, still waiting
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SplashScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    EcommerceTheme {
        SplashScreen()
    }
}
