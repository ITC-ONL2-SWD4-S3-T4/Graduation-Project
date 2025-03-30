package com.example.jobsearchapplication.data.repository

import com.example.jobsearchapplication.data.dataSources.remote.JobRemoteDataSource
import com.example.jobsearchapplication.data.mapper.toJobDomainModel
import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.domain.repository.JobsRepository
import javax.inject.Inject

class JobsRepositoryImpl @Inject constructor(
    private val jobRemoteDataSource: JobRemoteDataSource
): JobsRepository {
    override suspend fun fetchJobs(): List<JobsDomainModel> {
        return jobRemoteDataSource.fetchJobs().toJobDomainModel()
    }
}