package com.example.jobsearchapplication.domain.usecase

import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.domain.repository.JobsRepository
import javax.inject.Inject

class FetchIJobsUseCase @Inject constructor(
    private var jobsRepository: JobsRepository
) {
    suspend operator fun invoke(): List<JobsDomainModel>{
        return jobsRepository.fetchJobs()
    }
}