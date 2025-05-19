package com.example.jobsearchapplication.domain.usecase

import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.domain.repository.SavedJobRepository
import com.example.jobsearchapplication.domain.repository.TrackedJobsRepository
import com.example.jobsearchapplication.ui.common_components.JobStatus
import kotlinx.coroutines.flow.Flow

class SaveTrackedJobUseCase(private val repository: TrackedJobsRepository) {
    suspend operator fun invoke(job: JobsDomainModel) = repository.saveJob(job)
}

class GetTrackedJobsUseCase(private val repository: TrackedJobsRepository) {
    operator fun invoke(): Flow<List<JobsDomainModel>> = repository.getSavedJobs()
}

class DeleteTrackedJobUseCase(private val repository: TrackedJobsRepository) {
    suspend operator fun invoke(id: Long) = repository.deleteJob(id)
}

class UpdateTrackedJobStatusUseCase(
    private val repository: TrackedJobsRepository
) {
    suspend operator fun invoke(id: Long, newStatus: JobStatus) =
        repository.updateJobStatus(id, newStatus)
}

class DeleteAllTrackedJobsUseCase(
    private val repository: TrackedJobsRepository
) {
    suspend operator fun invoke() =
        repository.deleteAll()
}