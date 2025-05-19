package com.example.jobsearchapplication.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jobsearchapplication.ui.screens.SplashScreen
import com.example.jobsearchapplication.ui.screens.add_reminder_screen.ReminderSetupScreen
import com.example.jobsearchapplication.ui.screens.job_details_screen.JobDetailsScreen
import com.example.jobsearchapplication.ui.screens.job_search_screen.JobSearchScreen
import com.example.jobsearchapplication.ui.screens.login_and_signup.SignInScreen
import com.example.jobsearchapplication.ui.screens.login_and_signup.SignUpScreen
import com.example.jobsearchapplication.ui.screens.reminder_screen.ReminderScreen
import com.example.jobsearchapplication.ui.screens.saved_jobs.SavedJobsScreen
import com.example.jobsearchapplication.ui.screens.track_jobs_screen.TrackerListScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val context = LocalContext.current
    val logInManager = remember { LogInManager(context) }

//    val startDestination = remember {
//        if (logInManager.isLoggedIn()) {
//            Screens.JobSearchScreen.route
//        } else {
//            Screens.LoginScreen.route
//        }
//    }

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
//        startDestination
    ){

        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screens.LoginScreen.route) {
            SignInScreen(
                onNavigateToSignUp = { navController.navigate(Screens.SignUpScreen.route) },
                onSignInSuccess = {
                    logInManager.saveLoginStatus()
                    navController.navigate(Screens.JobSearchScreen.route){
                        popUpTo(Screens.LoginScreen.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screens.SignUpScreen.route) {
            SignUpScreen(
                onNavigateToSignIn = { navController.popBackStack() },
                onSignUpSuccess = {
                    logInManager.saveLoginStatus()
                    navController.navigate(Screens.JobSearchScreen.route){
                        popUpTo(Screens.LoginScreen.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screens.JobSearchScreen.route){


            JobSearchScreen (
                onJobItemClicked = { jobItem ->
                    navController.navigate(Screens.JobDetailsScreen.passId(jobItem.id))
                },
                navController = navController
            )
        }

        composable(
            route = Screens.JobDetailsScreen.route,
            arguments = listOf(
                navArgument("jobId"){
                    type = NavType.LongType
                }
            )
        ){ backStackEntry ->

            val jobId = backStackEntry.arguments?.getLong("jobId")
            if (jobId != null){
                JobDetailsScreen(
                    jobId,
                    onBackNavigation = {navController.popBackStack()}
                )
            }

        }

        composable(
            route = Screens.AddReminderScreen.route,
            arguments = listOf(
                navArgument("reminderId"){
                    type = NavType.IntType
                }
            )
        ){ backStackEntry ->

            val reminderId = backStackEntry.arguments?.getInt("reminderId")

            ReminderSetupScreen(
                reminderId = if (reminderId == -1) null else reminderId,
                onBackNavigation = {navController.popBackStack()}
            )


        }

        composable(route = Screens.SavedJobsScreen.route){
            SavedJobsScreen(
                onJobItemClicked = { jobItem ->
                    navController.navigate(Screens.JobDetailsScreen.passId(jobItem.id))
                },
                navController = navController
            )
        }

        composable(route = Screens.RemindersScreen.route){
            ReminderScreen(
                onReminderItemClicked = { reminderItem ->
                    navController.navigate(Screens.AddReminderScreen.passId(reminderItem.id))
                },
                onAddReminder = {
                    navController.navigate(Screens.AddReminderScreen.passId(-1))
                },
                navController = navController
            )
        }

        composable(route = Screens.TrackerScreen.route){
            TrackerListScreen(
                onJobItemClicked = { jobItem ->
                    navController.navigate(Screens.JobDetailsScreen.passId(jobItem.id))
                },
                navController = navController
            )
        }
}}