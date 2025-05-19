package com.example.jobsearchapplication.ui.screens.job_search_screen.searchBar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jobsearchapplication.ui.navigation.LogInManager
import com.example.jobsearchapplication.ui.navigation.Screens
import com.example.jobsearchapplication.ui.screens.job_search_screen.viewmodel.JobSearchViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    val viewModel: JobSearchViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier
            .height(100.dp)
            .padding(top = 30.dp)
            .fillMaxWidth(),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
                scope.launch { viewModel.onSearchQueryChanged(it) }
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(0.6f),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(0.6f),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                            //
                            scope.launch { viewModel.onSearchQueryChanged("") }
                            //
                        } else {
                            onCloseClicked()
                        }

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor  = Color.Transparent,
                cursorColor = Color.White.copy(alpha = 0.6f)
            ))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    navController: NavController
) {

    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val logInManager = remember { LogInManager(context) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    TopAppBar(
        title = {
            Text(
                text = "Job Search",
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
                onClick = {onSearchClicked()}
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }


            IconButton(
                onClick = {expanded = true}
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Log Out") },
                    onClick = {
                        expanded = false
                        logInManager.logOut()
                        navController.navigate(Screens.LoginScreen.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainAppBar(
    navController: NavController,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = onSearchTriggered,
                navController = navController
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}


