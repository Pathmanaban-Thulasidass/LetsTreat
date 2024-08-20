package com.example.letstreat

import com.example.letstreat.textView.CRUDFileStorage
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.letstreat.UIPages.userPages.MainView
import com.example.letstreat.datum.PreferencesManager
import com.example.letstreat.ui.theme.LetsTreatTheme
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel

class MainActivity : ComponentActivity() {

    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {

        val authViewModel: AuthViewModel by viewModels()
        val displayViewModel: DisplayItemsViewModel by viewModels()

        super.onCreate(savedInstanceState)
        preferencesManager = PreferencesManager(this)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT

//     window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                // Check authentication status
                val isAuthStatusAuthenticated = authViewModel.checkAuthStatus()
                Log.d("Splash Msg:", "Auth Status Checked: $isAuthStatusAuthenticated")

                if (!isAuthStatusAuthenticated) {
                    // If not authenticated, stop showing the splash screen immediately
                    Log.d("Splash Msg:", "User is not authenticated, dismissing splash screen.")
                    return@setKeepOnScreenCondition false
                }

                // Retrieve and set the email if available
                val tempCurrentEmail = preferencesManager.getString("currentEmail")
                if (tempCurrentEmail != null) {
                    authViewModel.currentEmail.value = tempCurrentEmail
                } else {
                    Log.d("Splash Msg:", "Email not found, keeping splash screen visible.")
                    return@setKeepOnScreenCondition true
                }

                // Check if hotel name and user name are available and not blank
                val hotelNameNotBlank = authViewModel.retrievedCurrentHotelDetails.value?.hotelName?.isNotBlank() ?: false
                val userNameNotBlank = authViewModel.retrievedCurrentUserDetail.value?.userName?.isNotBlank() ?: false

                Log.d("Splash Msg:", "Hotel Name Not Blank: $hotelNameNotBlank")
                Log.d("Splash Msg:", "User Name Not Blank: $userNameNotBlank")

                // Keep the splash screen visible until at least one condition is true
                !(hotelNameNotBlank || userNameNotBlank)
            }
        }







//        installSplashScreen()

        setContent {

            LetsTreatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(
                        authViewModel = authViewModel,
                        displayItemsViewModel = displayViewModel,
                        preferencesManager = preferencesManager
                    )
                }
            }
        }
    }
}
