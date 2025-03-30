package com.example.jobsearchapplication.ui.screens.reminder_screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.example.jobsearchapplication.MainActivity
import com.example.jobsearchapplication.SecondActivity
import com.example.jobsearchapplication.ui.screens.job_search_screen.BottomNavigationItem
import com.example.jobsearchapplication.ui.screens.job_search_screen.JobItem
import com.example.jobsearchapplication.ui.screens.reminder_screen.preview.fakeReminderList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun ReminderScreen(modifier: Modifier = Modifier) {

    val navigationBarItems = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Saved Jobs",
            selectedIcon = Icons.Filled.Save,
            unSelectedIcon = Icons.Outlined.Save
        ),
        BottomNavigationItem(
            title = "Tracker",
            selectedIcon = Icons.Filled.QueryStats,
            unSelectedIcon = Icons.Outlined.QueryStats
        ),
        BottomNavigationItem(
            title = "Reminder",
            selectedIcon = Icons.Filled.Notifications,
            unSelectedIcon = Icons.Outlined.Notifications
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(3)
    }

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier.fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Reminders",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        scrolledContainerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    actions = {
                        IconButton(
                            onClick = {  }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior

                )
            },
            bottomBar = {
                NavigationBar (
                    containerColor = MaterialTheme.colorScheme.background
                ){
                    navigationBarItems.forEachIndexed { index, bottomNavigationItem ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)

                            },
                            label = {
                                Text(
                                    bottomNavigationItem.title,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                    },
                            icon = {
                                BadgedBox(
                                    badge = {

                                    }
                                ) {
                                    Icon(
                                        imageVector = if(selectedItemIndex == index){
                                            bottomNavigationItem.selectedIcon
                                        }else bottomNavigationItem.unSelectedIcon,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.onSurface

                                    )
                                }
                            }
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                    },
                    containerColor = MaterialTheme.colorScheme.tertiary

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        ) { innerPadding ->

            LazyColumn (
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
            ){
                items(fakeReminderList){  remiderItem ->
                    ReminderItem(reminderUiModel = remiderItem)
                }
            }
        }
    }
}