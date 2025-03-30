package com.example.jobsearchapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jobsearchapplication.ui.screens.job_search_screen.JobSearchScreen
import com.example.jobsearchapplication.ui.screens.reminder_screen.ReminderScreen
import com.example.jobsearchapplication.ui.theme.JobSearchApplicationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobSearchApplicationTheme {
                ReminderScreen()
            }
        }
    }
}



