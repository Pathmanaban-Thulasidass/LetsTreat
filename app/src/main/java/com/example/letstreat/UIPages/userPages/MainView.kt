package com.example.letstreat.UIPages.userPages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.letstreat.Navigation
import com.example.letstreat.datum.PreferencesManager
import com.example.letstreat.R
import com.example.letstreat.navigation.AdminBottomInScreen
import com.example.letstreat.navigation.AdminInfoNavigationIcon
import com.example.letstreat.navigation.Screen
import com.example.letstreat.navigation.UserBottomInScreen
import com.example.letstreat.navigation.UserInfoNavigationIcon
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    authViewModel: AuthViewModel,
    displayItemsViewModel: DisplayItemsViewModel,
    preferencesManager: PreferencesManager
) {
    val controller = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var isLoginPortionPage by remember {
        mutableStateOf(true)
    }

    var isUserCartView by remember {
        mutableStateOf(true)
    }

    var isSuggestedFoodItemView by remember {
        mutableStateOf(false)
    }

    var isFoodReviewView by remember {
        mutableStateOf(false)
    }

    var isSummaryPageView by remember {
        mutableStateOf(false)
    }

    var isOrderSuccessView by remember {
        mutableStateOf(false)
    }

    // Update isLoginPortionPage when the route changes
    LaunchedEffect(currentRoute) {
        isLoginPortionPage = (Screen.GetStartedView.route == currentRoute
                || Screen.LoginPageView.route == currentRoute
                || Screen.SignUpPageView.route == currentRoute
                || Screen.HotelRegisterView.route == currentRoute)

        isUserCartView = Screen.UserBottomScreen.UserCart.bRoute == currentRoute

        isFoodReviewView =  "foodreviewpage/{path}" == currentRoute
        Log.d("Route :",currentRoute.toString())

        isSuggestedFoodItemView =
            (
                 Screen.SuggestedFoodScreen.Biriyani.route == currentRoute
                    || Screen.SuggestedFoodScreen.Noodles.route == currentRoute
                    || Screen.SuggestedFoodScreen.SouthIndian.route == currentRoute
                    || Screen.SuggestedFoodScreen.Chinese.route == currentRoute
                    || Screen.SuggestedFoodScreen.Cake.route == currentRoute
                    || Screen.SuggestedFoodScreen.Dosa.route == currentRoute
                    || Screen.SuggestedFoodScreen.Omellete.route == currentRoute
                    || Screen.SuggestedFoodScreen.Desserts.route == currentRoute
                    || Screen.SuggestedFoodScreen.NorthIndian.route == currentRoute
                    || Screen.SuggestedFoodScreen.Rolls.route == currentRoute
                    || Screen.SuggestedFoodScreen.GulabJamun.route == currentRoute
                    || Screen.SuggestedFoodScreen.Kebabs.route == currentRoute
                    || Screen.SuggestedFoodScreen.Pokoda.route == currentRoute
                    || Screen.SuggestedFoodScreen.Burger.route == currentRoute
                    || Screen.SuggestedFoodScreen.Shawarma.route == currentRoute
                    || Screen.SuggestedFoodScreen.Drinks.route == currentRoute
            )

        isSummaryPageView = Screen.FoodSummaryView.route == currentRoute

        isOrderSuccessView = Screen.OrderSuccessView.route == currentRoute
    }

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent, // Set transparent background for the status bar
            darkIcons = true // Set to true if you want dark icons (for light background)
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent // Set transparent background for the navigation bar
        )
    }

    Scaffold(
        topBar = {
            if (!isLoginPortionPage && !isOrderSuccessView) {
                if(isUserCartView || isFoodReviewView || isSuggestedFoodItemView || isSummaryPageView){
                    TopAppBar(
                        title = {
                            if(isUserCartView){
                                Text(
                                    text = "My Cart",
                                    modifier = Modifier.padding(start = 20.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            else if(isSuggestedFoodItemView){
                                Text(
                                    text = displayItemsViewModel.currentSuggestionFoodItemName.value,
                                    modifier = Modifier.padding(start = 20.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            else if(isSummaryPageView){
                                Text(
                                    text = "Order Summary",
                                    modifier = Modifier.padding(start = 20.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            else{
                                Text(
                                    text = displayItemsViewModel.currentReviewFoodItemName.value,
                                    modifier = Modifier.padding(start = 20.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    controller.navigateUp()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(
                                            start = 10.dp
                                        )
                                        .size(30.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = colorResource(id = R.color.darkbg)
                        )
                    )
                }
                else{
                    TopAppBar(
                        title = {

                        },
                        navigationIcon = {
                            if(authViewModel.isUserLoggedIn.value){
                                UserInfoNavigationIcon(authViewModel = authViewModel)
                            }
                            else{
                                AdminInfoNavigationIcon(authViewModel = authViewModel)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = colorResource(id = R.color.darkbg)
                        ),
                        actions = {
                            IconButton(
                                onClick = {
                                    //TODO navigate to notification Page
                                    if(authViewModel.isUserLoggedIn.value){
                                        controller.navigate(Screen.UserNotification.route)
                                    }
                                    else{
                                        controller.navigate(Screen.AdminNotification.route)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.whitesmoke),
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(end = 8.dp)
                                )
                            }
                        }
                    )
                }
            }
        },
        bottomBar = {
            if(!isLoginPortionPage && !isUserCartView && !isFoodReviewView && !isSummaryPageView && !isOrderSuccessView) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            alpha = 0.8f
                            shape = RoundedCornerShape(15.dp)
                            clip = true
                        }
                        .background(
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                ) {
                    BottomNavigation(
                        modifier = Modifier.wrapContentSize(),
                        backgroundColor = Color.Transparent
                    ) {
                        if(authViewModel.isUserLoggedIn.value){
                            UserBottomInScreen.forEach {
                                item ->
                                BottomNavigationItem(
                                    selected = currentRoute == item.bRoute,
                                    onClick = {
                                        controller.navigate(item.bRoute)
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = item.icon),
                                            contentDescription = null,
                                            tint = colorResource(id = R.color.whitesmoke),
                                            modifier = Modifier.size(35.dp)
                                        )
                                    },
                                    selectedContentColor = colorResource(id = R.color.darkbg),
                                    unselectedContentColor = colorResource(id = R.color.whitesmoke)
                                )
                            }
                        }
                        else{
                            AdminBottomInScreen.forEach { item ->
                                BottomNavigationItem(
                                    selected = currentRoute == item.bRoute,
                                    onClick = {
                                        controller.navigate(item.bRoute)
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = item.icon),
                                            contentDescription = null,
                                            tint = colorResource(id = R.color.whitesmoke),
                                            modifier = Modifier.size(35.dp)
                                        )
                                    },
                                    selectedContentColor = colorResource(id = R.color.darkbg),
                                    unselectedContentColor = colorResource(id = R.color.whitesmoke)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { innerPadding ->


        Navigation(
            authViewModel = authViewModel,
            displayItemsViewModel = displayItemsViewModel,
            navController = controller,
            preferencesManager = preferencesManager,
            pdValues = innerPadding
        )

    }
}