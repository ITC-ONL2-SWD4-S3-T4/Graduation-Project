package com.example.jobsearchapplication.ui.screens.job_search_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobsearchapplication.R
import com.example.jobsearchapplication.ui.screens.job_search_screen.preview.fakeJobList
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun JobItem(
    modifier: Modifier = Modifier,
    jobUiModel: JobUiModel,

) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp),
//        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {  },

    ){
        Column (
            modifier = Modifier.padding(16.dp)
//                .fillMaxWidth()
        ){
            Row (
//            modifier = Modifier.padding(bottom = 5.dp)
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
//                    imageVector = ImageVector.vectorResource(id = R.drawable.work),
                    imageVector = Icons.Outlined.Work,
                    contentDescription = "Job Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = jobUiModel.title,
                    style = MaterialTheme.typography.titleMedium,
//                    color = MaterialTheme.colorScheme.onSurface,

                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    //
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

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


            // Location
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

//            Text(
//                text = "Salary: ${jobUiModel.salary}",
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onSurface
//            )

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

            Row (
//            modifier = Modifier.padding(2.dp)
            ){
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("View", style = MaterialTheme.typography.labelLarge)
                }
                Spacer(modifier = Modifier.width(5.dp))
                OutlinedButton(
                    onClick = {},
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Text("Save", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }

}


@Preview(
    showBackground = true,
    backgroundColor = 0x00ff44
)
@Composable
private fun JobItemPreview() {
//    JobItem(jobUiModel = fakeJobList.first())
}