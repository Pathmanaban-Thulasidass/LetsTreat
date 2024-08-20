package com.example.letstreat.UIPages.userPages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letstreat.R
import com.example.letstreat.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OrderSuccessScreen(
    navController: NavController
) {
    var isVisible by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    // Trigger the animation after a slight delay
    LaunchedEffect(Unit) {
        isVisible = true
        scope.launch {
            delay(3500)
            navController.navigate(Screen.UserBottomScreen.UserHome.route)
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.green3))
    ) {
        // Animated visibility for the content
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(1500)),
            exit = fadeOut(animationSpec = tween(1500)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Order Confirmed",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.whitesmoke)
                )
            }
        }
    }
}
