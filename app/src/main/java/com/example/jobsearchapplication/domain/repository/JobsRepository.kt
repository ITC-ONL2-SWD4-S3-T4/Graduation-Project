package com.example.jobsearchapplication.domain.repository

import com.example.jobsearchapplication.domain.model.JobsDomainModel

interface JobsRepository {
    suspend fun fetchJobs(): List<JobsDomainModel>
}