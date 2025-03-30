package com.example.jobsearchapplication.ui.navigation

sealed class Screens (val route: String){
    data object JobSearchScreen: Screens("job_search_screen")
}