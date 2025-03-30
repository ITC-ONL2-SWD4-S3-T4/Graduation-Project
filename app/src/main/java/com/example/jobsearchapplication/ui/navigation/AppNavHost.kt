package com.example.jobsearchapplication.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jobsearchapplication.ui.screens.job_search_screen.JobSearchScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.JobSearchScreen.route
    ){

        composable(route = Screens.JobSearchScreen.route){
            JobSearchScreen()


    }
}}