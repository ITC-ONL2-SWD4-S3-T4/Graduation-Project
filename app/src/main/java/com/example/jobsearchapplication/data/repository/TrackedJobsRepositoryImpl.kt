package com.example.jobsearchapplication.data.repository
import com.example.jobsearchapplication.data.dataSources.local.dao.JobDao
import com.example.jobsearchapplication.data.dataSources.local.dao.TrackedJobsDao
import com.example.jobsearchapplication.data.mapper.toEntity
import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.domain.repository.SavedJobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import com.example.jobsearchapplication.data.mapper.toDomain
import com.example.jobsearchapplication.data.mapper.toTrackedJobsEntity
import com.example.jobsearchapplication.domain.repository.TrackedJobsRepository
import com.example.jobsearchapplication.ui.common_components.JobStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.withContext


class TrackedJobsRepositoryImpl @Inject constructor(private val dao: TrackedJobsDao) :
    TrackedJobsRepository {
    override suspend fun saveJob(job: JobsDomainModel) = withContext(Dispatchers.IO) {
        dao.insert(job.toTrackedJobsEntity())
    }

    override fun getSavedJobs(): Flow<List<JobsDomainModel>> {
        return dao.getAll().distinctUntilChanged().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun deleteJob(id: Long) = withContext(Dispatchers.IO){
        dao.delete(id)
    }

    override suspend fun updateJobStatus(id: Long, newStatus: JobStatus) =
        withContext(Dispatchers.IO) {
            dao.updateJobStatus(id, newStatus)
        }

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
    }

}
