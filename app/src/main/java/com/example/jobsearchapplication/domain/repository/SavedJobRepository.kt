package com.example.jobsearchapplication.domain.repository

import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.ui.common_components.JobStatus
import kotlinx.coroutines.flow.Flow

interface SavedJobRepository{
    suspend fun saveJob(job: JobsDomainModel)
    fun getSavedJobs(): Flow<List<JobsDomainModel>>
    suspend fun deleteJob(id: Long)
    suspend fun isJobSaved(id: Long): Boolean
    suspend fun updateJobStatus(id: Long, newStatus: JobStatus)
    suspend fun deleteAll()
}