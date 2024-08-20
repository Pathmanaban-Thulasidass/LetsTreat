package com.example.letstreat.navigation

import androidx.annotation.DrawableRes
import com.example.letstreat.R

sealed class Screen(val route : String) {

    //Authentication Pages
    data object GetStartedView : Screen("getstarted")
    data object LoginPageView : Screen("loginpage")
    data object SignUpPageView : Screen("signuppage")
    data object HotelRegisterView : Screen("hotelregistration")


    //User Pages
    data object UserNotification : Screen("usernotification")
    data object FoodItemReviewPage : Screen("foodreviewpage")
    data object FoodSummaryView : Screen("foodsummaryview")
    data object OrderSuccessView : Screen("ordersuccessview")


    //Admin Pages
    data object AdminFoodItemEditPage : Screen("adminfooditemeditpage")
    data object AdminNotification : Screen("adminnotification")


    sealed class AdminBottomScreen(val bRoute : String, @DrawableRes val icon : Int)
        :Screen(route = bRoute) {

        data object AdminHome : AdminBottomScreen(
            "adminhome",
            R.drawable.ic_home
        )

        data object AdminHistory : AdminBottomScreen(
            "adminhistory",
            R.drawable.ic_history
        )

        data object AdminAddItem : AdminBottomScreen(
            "adminadditem",
            R.drawable.ic_add
        )

        data object AdminSettings : AdminBottomScreen(
            "adminsettings",
            R.drawable.ic_settings
        )

        data object AdminAccount : AdminBottomScreen(
            "adminaccount",
            R.drawable.ic_account
        )
    }

    sealed class UserBottomScreen(val bRoute : String, @DrawableRes val icon : Int)
        : Screen(bRoute){

        data object UserHome : UserBottomScreen(
            "userhome",
            R.drawable.ic_home
        )

        data object UserCart : UserBottomScreen(
            "usercart",
            R.drawable.ic_cart
        )

        data object UserSetting : UserBottomScreen(
            "usersetting",
            R.drawable.ic_settings
        )

        data object UserAccount : UserBottomScreen(
            "useraccount",
            R.drawable.ic_account
        )

    }

    sealed class SuggestedFoodScreen(val sfRoute : String,@DrawableRes val img : Int)
        : Screen(route = sfRoute){

       data object Biriyani : SuggestedFoodScreen(
           "Biriyani",
           R.drawable.biriyani
       )

        data object SouthIndian : SuggestedFoodScreen(
            "South Indian",
            R.drawable.southindian
        )

        data object Chinese : SuggestedFoodScreen(
            "Chinese",
            R.drawable.chinese
        )

        data object Cake : SuggestedFoodScreen(
            "Cake",
            R.drawable.cake
        )

        data object Dosa : SuggestedFoodScreen(
            "Dosa",
            R.drawable.dosa
        )

        data object Omellete : SuggestedFoodScreen(
            "Omellete",
            R.drawable.omellete
        )

        data object Desserts : SuggestedFoodScreen(
            "Desserts",
            R.drawable.desserts
        )

        data object NorthIndian : SuggestedFoodScreen(
            "North Indian",
            R.drawable.northindianfood
        )

        data object Rolls : SuggestedFoodScreen(
            "Rolls",
            R.drawable.rolls
        )

        data object GulabJamun : SuggestedFoodScreen(
            "Gulab Jamun",
            R.drawable.gulabjamun
        )

        data object Noodles : SuggestedFoodScreen(
            "Noodles",
            R.drawable.noodles
        )

        data object Kebabs : SuggestedFoodScreen(
            "Kebabs",
            R.drawable.kebabs
        )

        data object Pokoda : SuggestedFoodScreen(
            "Pokoda",
            R.drawable.pokoda
        )

        data object Burger : SuggestedFoodScreen(
            "Burger",
            R.drawable.burger
        )

        data object Shawarma : SuggestedFoodScreen(
            "Shawarma",
            R.drawable.sharwarma
        )

        data object Drinks : SuggestedFoodScreen(
            "Drinks",
            R.drawable.drinks
        )

    }


}

val AdminBottomInScreen = listOf(
    Screen.AdminBottomScreen.AdminHome,
    Screen.AdminBottomScreen.AdminHistory,
    Screen.AdminBottomScreen.AdminAddItem,
    Screen.AdminBottomScreen.AdminSettings,
    Screen.AdminBottomScreen.AdminAccount
)

val UserBottomInScreen = listOf(
    Screen.UserBottomScreen.UserHome,
    Screen.UserBottomScreen.UserCart,
    Screen.UserBottomScreen.UserSetting,
    Screen.UserBottomScreen.UserAccount
)

val SuggestedFoodsInScreen = listOf(
    Screen.SuggestedFoodScreen.Biriyani,
    Screen.SuggestedFoodScreen.Noodles,
    Screen.SuggestedFoodScreen.SouthIndian,
    Screen.SuggestedFoodScreen.Chinese,
    Screen.SuggestedFoodScreen.Cake,
    Screen.SuggestedFoodScreen.Dosa,
    Screen.SuggestedFoodScreen.Omellete,
    Screen.SuggestedFoodScreen.Desserts,
    Screen.SuggestedFoodScreen.NorthIndian,
    Screen.SuggestedFoodScreen.Rolls,
    Screen.SuggestedFoodScreen.GulabJamun,
    Screen.SuggestedFoodScreen.Kebabs,
    Screen.SuggestedFoodScreen.Pokoda,
    Screen.SuggestedFoodScreen.Burger,
    Screen.SuggestedFoodScreen.Shawarma,
    Screen.SuggestedFoodScreen.Drinks
)