package com.example.jobsearchapplication.ui.screens.job_search_screen


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobsearchapplication.R
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jobsearchapplication.ui.common_components.BottomNavigationItem
import com.example.jobsearchapplication.ui.common_components.DeleteAlertDialog
import com.example.jobsearchapplication.ui.common_components.JobStatus
import com.example.jobsearchapplication.ui.screens.saved_jobs.viewmodel.SavedJobViewModel
import com.example.jobsearchapplication.ui.screens.track_jobs_screen.viewmodel.TrackedJobsViewModel


@Composable
fun JobItem(
    modifier: Modifier = Modifier,
    jobUiModel: JobUiModel,
    onJobItemClicked: (jobItem: JobUiModel) -> Unit,
    onSave: () -> Unit,
    onDelete: () -> Unit,
    jobSearchJobItem: Boolean,
    onTrackJob: () -> Unit
) {



    val trackedJobViewModel: TrackedJobsViewModel = hiltViewModel()
    val savedJobViewModel: SavedJobViewModel = hiltViewModel()

    var showUnSaveJobDialog by remember { mutableStateOf(false) }


    var status by rememberSaveable { mutableStateOf(jobUiModel.status) }

    val isApplied = status == JobStatus.APPLIED

    val context = LocalContext.current

    val isSavedUI by savedJobViewModel.isJobSavedState(jobUiModel.id).collectAsState()



    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onJobItemClicked(jobUiModel) },

        ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Outlined.Work,
                    contentDescription = "Job Icon",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = jobUiModel.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = if (isSavedUI) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = "Save Icon",
                    modifier = Modifier.size(24.dp)
                        .clickable {
                            if (isSavedUI) {
                                showUnSaveJobDialog = true
                            } else{
                                onSave()
                            }
                        }
                )

                if (showUnSaveJobDialog) {
                    DeleteAlertDialog(
                        alertText = "Are you sure you want to UnSave this Job?",
                        onDismiss = { showUnSaveJobDialog = false },
                        onConfirm = {
                            onDelete()
                            showUnSaveJobDialog = false
                        }
                    )
                }

            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = "Location Icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = jobUiModel.company ,
                    style = MaterialTheme.typography.titleSmall,

                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),

                    )
            }

            Spacer(modifier = Modifier.height(5.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = jobUiModel.location,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.dollar),
                    contentDescription = "Salary Icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Salary: \$${jobUiModel.salary_min}K",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row (){

                Spacer(modifier = Modifier.width(5.dp))

                Button(
                    onClick = {
                        when{
                            jobSearchJobItem -> {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(jobUiModel.redirect_url))
                                context.startActivity(intent)
                            }
                            isApplied -> jobUiModel?.status = JobStatus.APPLIED
                            else -> {
                                status = JobStatus.APPLIED
                                jobUiModel?.status = JobStatus.APPLIED
                                trackedJobViewModel.updateJobStatus(jobUiModel.id, JobStatus.APPLIED)
                                savedJobViewModel.updateJobStatus(jobUiModel.id, JobStatus.APPLIED)
                                onTrackJob()
                            }
                        }

                    },
                    colors = when{
                        jobSearchJobItem -> ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                        else -> ButtonDefaults.buttonColors(
                            containerColor = if (isApplied) Color.Gray else MaterialTheme.colorScheme.primary
                        )
                    },
                    enabled = when{
                        jobSearchJobItem -> true
                        else -> !isApplied
                    }

                ) {
                    when{
                        jobSearchJobItem -> Text("Apply", style = MaterialTheme.typography.labelLarge)
                        isApplied -> Text("Moved to Tracker!", style = MaterialTheme.typography.labelLarge)
                        else -> Text("Mark as Applied", style = MaterialTheme.typography.labelLarge)
                    }

                }
            }
        }
    }

}