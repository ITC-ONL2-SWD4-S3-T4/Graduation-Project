package com.example.jobsearchapplication.ui.screens.job_search_screen

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jobsearchapplication.MainActivity
import com.example.jobsearchapplication.ui.common_components.BottomBar
import com.example.jobsearchapplication.ui.screens.job_search_screen.preview.fakeJobList
import com.example.jobsearchapplication.ui.screens.job_search_screen.searchBar.MainAppBar
import com.example.jobsearchapplication.ui.screens.job_search_screen.searchBar.SearchViewModel
import com.example.jobsearchapplication.ui.screens.job_search_screen.searchBar.SearchWidgetState
import com.example.jobsearchapplication.ui.screens.job_search_screen.viewmodel.FilterOptions
import com.example.jobsearchapplication.ui.screens.job_search_screen.viewmodel.JobSearchViewModel
import com.example.jobsearchapplication.ui.screens.saved_jobs.viewmodel.SavedJobViewModel
import com.example.jobsearchapplication.ui.theme.Grey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobSearchScreen(
    onJobItemClicked: (jobItem: JobUiModel) -> Unit,
    navController: NavController
) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val jobSearchViewModel: JobSearchViewModel = hiltViewModel()
    val searchViewModel: SearchViewModel = hiltViewModel()


    val savedJobViewModel: SavedJobViewModel = hiltViewModel()
    val jobList by jobSearchViewModel.jobList.collectAsStateWithLifecycle()

    val filterOptions by jobSearchViewModel.filterOptions.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = true) {
        jobSearchViewModel.fetchJobs()
    }

    val context = LocalContext.current

    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MainAppBar(
                    navController = navController,
                    searchWidgetState = searchWidgetState,
                    searchTextState = searchTextState,
                    onTextChange = {
                        searchViewModel.updateSearchTextState(newValue = it)
                    },
                    onCloseClicked = {
                        searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)

                    },
                    onSearchClicked = {
                        Log.d("Searched Text", it)
                    },
                    onSearchTriggered = {
                        searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                    }
                )
            },
            bottomBar = {
                BottomBar(
                    currentRoute = currentRoute,
                    navController = navController
                )

            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                JobFilters(currentFilters = filterOptions,
                    onFilterChanged = { jobSearchViewModel.updateFilterOptions(it) })

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(jobList) { jobItem ->

                        JobItem(
                            jobUiModel = jobItem,
                            onJobItemClicked = {
                                onJobItemClicked(it)
                            },
                            onSave = {
                                savedJobViewModel.saveJob(jobItem)
                            },
                            onDelete = {savedJobViewModel.deleteJob(jobItem.id)},
                            jobSearchJobItem = true,
                            onTrackJob = {}

                        )
                    }
                }
            }

        }
    }
}




@Composable
fun JobFilters(
    currentFilters: FilterOptions,
    onFilterChanged: (FilterOptions) -> Unit
) {
    var expandedContractType by remember { mutableStateOf(false) }
    var expandedDate by remember { mutableStateOf(false) }
    var expandedContractTime by remember { mutableStateOf(false) }
    var expandedSalary by remember { mutableStateOf(false) }

    var minSalary by remember { mutableStateOf(currentFilters.minSalary?.toString() ?: "") }
    var maxSalary by remember { mutableStateOf(currentFilters.maxSalary?.toString() ?: "") }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()) ,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterButton("Contract Type", expandedContractType, { expandedContractType = !expandedContractType }) {
            DropdownMenuItem(text = { Text("Permanent") }, onClick = {
                onFilterChanged(currentFilters.copy(contractType = "permanent"))
                expandedContractType  = false })
            DropdownMenuItem(text = { Text("Contract") }, onClick = {
                onFilterChanged(currentFilters.copy(contractType = "contract"))
                expandedContractType = false })

            DropdownMenuItem(
                text = { Text("All Types") },
                onClick = {
                    onFilterChanged(currentFilters.copy(contractType = ""))
                    expandedContractType = false
                }
            )
        }

        FilterButton("Date posted", expandedDate, { expandedDate = !expandedDate }) {
            DropdownMenuItem(text = { Text("Last 24 hours") }, onClick = {
                onFilterChanged(currentFilters.copy(datePosted = "24h"))
                expandedDate = false })
            DropdownMenuItem(text = { Text("Last 7 days") }, onClick = {
                onFilterChanged(currentFilters.copy(datePosted = "7d"))
                expandedDate = false })
            DropdownMenuItem(text = { Text("Last 30 days") }, onClick = {
                onFilterChanged(currentFilters.copy(datePosted = "30d"))
                expandedDate = false })

            DropdownMenuItem(
                text = { Text("Any time") },
                onClick = {
                    onFilterChanged(currentFilters.copy(datePosted = ""))
                    expandedDate = false
                }
            )
        }

        FilterButton("Contract Time", expandedContractTime, { expandedContractTime = !expandedContractTime }) {
            DropdownMenuItem(text = { Text("Full Time") }, onClick = {
                onFilterChanged(currentFilters.copy(contractTime = "full_time"))
                expandedContractTime = false })
            DropdownMenuItem(text = { Text("Part Time") }, onClick = {
                onFilterChanged(currentFilters.copy(contractTime = "part_time"))
                expandedContractTime = false })

            DropdownMenuItem(
                text = { Text("Any") },
                onClick = {
                    onFilterChanged(currentFilters.copy(contractTime = ""))
                    expandedContractTime = false
                }
            )
        }

        FilterButton("Salary Range", expandedSalary, { expandedSalary = !expandedSalary }) {
            DropdownMenuItem(
                text = {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Enter Salary Range")

                        OutlinedTextField(
                            value = minSalary,
                            onValueChange = { minSalary = it },
                            label = { Text("Min Salary") },
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = maxSalary,
                            onValueChange = { maxSalary = it },
                            label = { Text("Max Salary") },
                            singleLine = true
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    onFilterChanged(
                                        currentFilters.copy(
                                            minSalary = minSalary.toDoubleOrNull(),
                                            maxSalary = maxSalary.toDoubleOrNull()
                                        )
                                    )
                                    expandedSalary = false
                                },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Apply")
                            }

                            Button(
                                onClick = {
                                    onFilterChanged(
                                        currentFilters.copy(
                                            minSalary = null,
                                            maxSalary = null
                                        )
                                    )
                                    minSalary = ""
                                    maxSalary = ""
                                    expandedSalary = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.errorContainer,
                                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Clear")
                            }
                        }


                    }
                },
                onClick = {  }
            )
        }
    }
}


@Composable
fun FilterButton(
    text: String,
    expanded: Boolean,
    onClick: () -> Unit,
    dropdownContent: @Composable () -> Unit
) {
    Column {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Grey,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant)
            ,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .height(45.dp)
                .padding(vertical = 4.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 2.dp
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text, fontSize = 14.sp, color = Color.Black)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
            }
        }
        DropdownMenu(expanded = expanded, onDismissRequest = onClick) {
            dropdownContent()
        }
    }
}






@Preview
@Composable
 fun JobSearchScreenPreview() {
//    JobSearchScreen()
}