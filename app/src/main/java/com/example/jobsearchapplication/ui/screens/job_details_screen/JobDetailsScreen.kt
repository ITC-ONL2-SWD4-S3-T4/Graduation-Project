package com.example.jobsearchapplication.ui.screens.job_details_screen

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jobsearchapplication.ui.theme.JobSearchApplicationTheme
import com.example.jobsearchapplication.R
import com.example.jobsearchapplication.ui.common_components.JobStatus
import com.example.jobsearchapplication.ui.screens.job_search_screen.viewmodel.JobSearchViewModel
import com.example.jobsearchapplication.ui.screens.saved_jobs.viewmodel.SavedJobViewModel
import com.example.jobsearchapplication.ui.screens.track_jobs_screen.viewmodel.TrackedJobsViewModel
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(
    jobId: Long,
    onBackNavigation: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth() )
                    {Text("Job Details", fontWeight = FontWeight.Bold ,
                        modifier = Modifier.padding(end = 48.dp), color= Color.White)} },
                navigationIcon = {
                    IconButton(onClick = { onBackNavigation() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back",
                            modifier = Modifier.size(28.dp), tint= Color.White)
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                JobDetailsContent(
                    jobId = jobId
                )
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JobDetailsContent(
    jobId: Long,
) {
    val jobSearchViewModel: JobSearchViewModel = hiltViewModel()
    val savedJobViewModel: SavedJobViewModel = hiltViewModel()
    val trackedJobViewModel: TrackedJobsViewModel = hiltViewModel()

    val jobList by jobSearchViewModel.jobList.collectAsStateWithLifecycle()

    val savedJobs by savedJobViewModel.savedJobs.collectAsState()
    val trackedJobs by trackedJobViewModel.savedJobs.collectAsState()

    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")


    val job = remember(jobId, jobList, savedJobs, trackedJobs){

        trackedJobs.find { it.id == jobId }?.let { trackedJob ->
            jobList.find { it.id == jobId }?.copy(
                status = trackedJob.status
            ) ?: trackedJob
        }
            ?: savedJobs.find { it.id == jobId }?.let { savedJob ->
                jobList.find { it.id == jobId }?.copy(
                    status = savedJob.status
                ) ?: savedJob
            }
            ?: jobList.find { it.id == jobId }
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = job?.title ?: "N/A",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = job?.company ?: "N/A",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location Icon",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = job?.location ?: "N/A",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = job?.created?.format(formatter) ?: "N/A",
                        color = Color.Gray,
                        fontSize = 10.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        InfoCard(
                            "Salary",
                            "$${job?.salary_min }",
                            R.drawable.dollar,
                        )
                        InfoCard(
                            "Category",
                            job?.category ?: "N/A",
                            R.drawable.work,
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        InfoCard(
                            "Contract Time",
                            job?.contract_time ?: "N/A",
                            R.drawable.work,
                        )
                        InfoCard(
                            "Contract Type" , job?.contract_type ?: "N/A",
                            R.drawable.work,
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    TabSection(text = job?.description ?: "N/A")
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }

            val context = LocalContext.current
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job?.redirect_url))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Apply", style = MaterialTheme.typography.labelLarge)
                }
                Spacer(modifier = Modifier.width(16.dp))

                val isApplied by rememberUpdatedState(newValue = job?.status.toString() == "APPLIED")

                Button(
                    onClick = {
                        when{
                            isApplied -> job?.status = JobStatus.APPLIED
                            else -> {

                                job?.let {
                                    job.status = JobStatus.APPLIED
                                    trackedJobViewModel.saveJob(job)
                                    trackedJobViewModel.updateJobStatus(job.id, JobStatus.APPLIED)
                                    savedJobViewModel.updateJobStatus(job.id, JobStatus.APPLIED)
                                }
                            }
                        }

                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isApplied) Color.Gray else MaterialTheme.colorScheme.primary
                    ),
                    enabled = !isApplied
                ) {
                    when{
                        isApplied -> Text("Moved to Tracker!", style = MaterialTheme.typography.labelLarge)
                        else -> Text("Mark as Applied", style = MaterialTheme.typography.labelLarge)
                    }

                }
            }
        }


    }
}

@Composable
fun InfoCard(title: String, value: String, icon: Int) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(100.dp)
            .padding(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Icon(
                    imageVector = ImageVector.vectorResource(id = icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(18.dp)

                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.teal_700)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun TabSection(text: String) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("Description")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = selectedTab,
            Modifier.height(80.dp),
            containerColor = Color.Black,
            contentColor = colorResource(id = R.color.teal_700)
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            title,
                            color = if (selectedTab == index) colorResource(id = R.color.teal_700) else Color.Gray
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        when (selectedTab) {
            0 -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Job Description",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 15.sp,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}