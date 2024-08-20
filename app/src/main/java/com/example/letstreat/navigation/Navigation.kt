package com.example.letstreat

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.letstreat.UIPages.adminPages.AdminAccountView
import com.example.letstreat.UIPages.adminPages.AdminHomePage
import com.example.letstreat.UIPages.adminPages.AdminNotification
import com.example.letstreat.UIPages.adminPages.FoodItemAddView
import com.example.letstreat.UIPages.adminPages.FoodItemEditView
import com.example.letstreat.UIPages.authUIpages.GetStartedView
import com.example.letstreat.UIPages.authUIpages.HotelRegistrationView
import com.example.letstreat.UIPages.authUIpages.LoginPage
import com.example.letstreat.UIPages.authUIpages.SignUpPageView
import com.example.letstreat.UIPages.userPages.CartView
import com.example.letstreat.UIPages.userPages.FoodReviewView
import com.example.letstreat.UIPages.userPages.OrderSuccessScreen
import com.example.letstreat.UIPages.userPages.SuggestedFoodItem
import com.example.letstreat.UIPages.userPages.SummaryView
import com.example.letstreat.UIPages.userPages.UserAccount
import com.example.letstreat.UIPages.userPages.UserHomePage
import com.example.letstreat.UIPages.userPages.UserNotification
import com.example.letstreat.datum.PreferencesManager
import com.example.letstreat.navigation.Screen
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel

@Composable
fun Navigation(
    authViewModel: AuthViewModel,
    displayItemsViewModel: DisplayItemsViewModel,
    navController : NavController,
    preferencesManager: PreferencesManager,
    pdValues : PaddingValues
) {


    NavHost(navController = navController as NavHostController, startDestination = Screen.GetStartedView.route){

        //Authentication View Pages
        composable(Screen.GetStartedView.route){
            GetStartedView(
                authViewModel = authViewModel,
                navController = navController,
                preferencesManager = preferencesManager,
                displayItemsViewModel = displayItemsViewModel
            )
        }

        composable(Screen.LoginPageView.route){
            LoginPage(
                authViewModel = authViewModel,
                navController = navController,
                preferencesManager = preferencesManager,
                displayItemsViewModel = displayItemsViewModel
            )
        }
        composable(Screen.SignUpPageView.route){
            SignUpPageView(
                authViewModel = authViewModel ,
                navController = navController,
                preferencesManager = preferencesManager,
                displayItemsViewModel = displayItemsViewModel
            )
        }
        composable(Screen.HotelRegisterView.route){
            HotelRegistrationView(
                authViewModel = authViewModel,
                navController = navController,
                preferencesManager = preferencesManager
            )
        }

        //Admin Pages
        composable(Screen.AdminBottomScreen.AdminHome.route){
            AdminHomePage(
                authViewModel = authViewModel,
                navController = navController,
                displayItemsViewModel = displayItemsViewModel
            )
        }

        composable(Screen.AdminBottomScreen.AdminHistory.route){

        }

        composable(Screen.AdminBottomScreen.AdminAddItem.route){
            FoodItemAddView(
                authViewModel = authViewModel,
                displayItemsViewModel = displayItemsViewModel,
                navController = navController
            )
        }

        composable(Screen.AdminBottomScreen.AdminSettings.route){

        }

        composable(Screen.AdminBottomScreen.AdminAccount.route){
            AdminAccountView(
                authViewModel = authViewModel,
                navController = navController
            )
        }

        composable(Screen.AdminFoodItemEditPage.route){
            FoodItemEditView()
        }

        composable(Screen.AdminNotification.route){
            AdminNotification()
        }



        //User Pages
        composable(Screen.UserBottomScreen.UserHome.bRoute){
            UserHomePage(
                authViewModel = authViewModel,
                navController = navController,
                displayItemsViewModel = displayItemsViewModel
            )
        }

        composable(Screen.UserNotification.route){
            UserNotification()
        }

        composable(Screen.UserBottomScreen.UserAccount.bRoute){
            UserAccount(
                authViewModel = authViewModel,
                navController = navController
            )
        }

        composable(Screen.UserBottomScreen.UserSetting.bRoute){

        }

        composable(Screen.UserBottomScreen.UserCart.bRoute){
            CartView(
                displayItemsViewModel = displayItemsViewModel,
                navController = navController
            )
        }

        //Suggested Food Items

        composable(Screen.SuggestedFoodScreen.Biriyani.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Biriyani.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Noodles.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Noodles.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.SouthIndian.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.SouthIndian.sfRoute,
                navController = navController

            )
        }

        composable(Screen.SuggestedFoodScreen.Chinese.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Chinese.sfRoute,
                navController = navController

            )
        }

        composable(Screen.SuggestedFoodScreen.Cake.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Cake.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Dosa.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Dosa.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Omellete.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Omellete.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Desserts.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Desserts.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.NorthIndian.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.NorthIndian.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Rolls.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Rolls.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.GulabJamun.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.GulabJamun.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Kebabs.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Kebabs.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Pokoda.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Pokoda.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Burger.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Burger.sfRoute,
                navController = navController
            )
        }

        composable(Screen.SuggestedFoodScreen.Shawarma.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Shawarma.sfRoute,
                navController = navController

            )
        }

        composable(Screen.SuggestedFoodScreen.Drinks.sfRoute){
            SuggestedFoodItem(
                displayItemsViewModel = displayItemsViewModel,
                foodCategoryName = Screen.SuggestedFoodScreen.Drinks.sfRoute,
                navController = navController
            )
        }

        //foodReviewPage
        composable(
            Screen.FoodItemReviewPage.route + "/{path}",
            arguments = listOf(
                navArgument("path"){
                    type = NavType.StringType
                    defaultValue = "Sorry 1"
                    nullable = false
                }
            )
        ){
            val path: String = it.arguments?.getString("path") ?: "Sorry 2"
//            val path = if(it.arguments != null) it.arguments?.getString("path") else "Sorry 2"
//            if (path != null) {
//                FoodReviewView(path = path)
//            }
            FoodReviewView(
                path = path,
                displayItemsViewModel = displayItemsViewModel,
                navController = navController
            )
        }

        composable(Screen.FoodSummaryView.route){
            SummaryView(
                authViewModel = authViewModel,
                displayItemsViewModel = displayItemsViewModel,
                navController = navController
            )
        }

        composable(Screen.OrderSuccessView.route){
            OrderSuccessScreen(
                navController = navController
            )
        }


    }

}