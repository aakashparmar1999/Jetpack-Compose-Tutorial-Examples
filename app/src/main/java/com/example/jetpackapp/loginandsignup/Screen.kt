package com.example.jetpackapp.loginandsignup

/*const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_NAME = "name"*/

sealed class Screen(val route: String) {
    object Dashboard : Screen(route = "dashboard_screen")
    object SignUp : Screen(route = "signup_screen")

    /*   object Detail : Screen(route = "detail_screen/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_NAME}") {
           fun passNameAndId(
               id: Int,
               name: String
           ): String {
               return "detail_screen/$id/$name"
           }
       }*/
    object SignIn : Screen(route = "signin_screen")

}
