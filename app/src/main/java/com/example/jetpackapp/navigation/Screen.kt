package com.example.jetpackapp.navigation

const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_NAME = "name"

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
 /*   object Detail : Screen(route = "detail_screen/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_NAME}") {
        fun passNameAndId(
            id: Int,
            name: String
        ): String {
            return "detail_screen/$id/$name"
        }
    }*/
    object Detail : Screen(route = "detail_screen?id={id}&name={name}") {
        fun passId(
            id: Int,
            name: String
        ): String {
            return "detail_screen?id=$id&name=$name"
        }
    }
}
