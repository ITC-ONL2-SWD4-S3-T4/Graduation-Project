package com.example.jobsearchapplication.ui.screens.saved_jobs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.domain.usecase.DeleteAllSavedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.DeleteJobUseCase
import com.example.jobsearchapplication.domain.usecase.GetSavedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.IsJobSavedUseCase
import com.example.jobsearchapplication.domain.usecase.SaveJobUseCase
import com.example.jobsearchapplication.domain.usecase.UpdateSavedJobsStatusUseCase
import com.example.jobsearchapplication.domain.usecase.UpdateTrackedJobStatusUseCase
import com.example.jobsearchapplication.ui.common_components.JobStatus
import com.example.jobsearchapplication.ui.mapper.toDomain
import com.example.jobsearchapplication.ui.screens.job_search_screen.JobUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.jobsearchapplication.ui.mapper.toJobsUiModel
import com.example.jobsearchapplication.ui.mapper.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


@HiltViewModel
class SavedJobViewModel @Inject constructor(
    private val saveJobUseCase: SaveJobUseCase,
    private val getSavedJobsUseCase: GetSavedJobsUseCase,
    private val deleteJobUseCase: DeleteJobUseCase,
    private val isJobSavedUseCase: IsJobSavedUseCase,
    private val updateSavedJobStatusUseCase: UpdateSavedJobsStatusUseCase,
    private val deleteAllSavedJobsUseCase: DeleteAllSavedJobsUseCase
) : ViewModel() {


    val savedJobs = getSavedJobsUseCase()
        .map { jobs ->
            jobs.map { it.toUi() }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )



    private val _savedJobIds = getSavedJobsUseCase()
        .map { jobs -> jobs.map { it.id }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptySet()
        )

    fun isJobSavedState(jobId: Long): StateFlow<Boolean> {
        return _savedJobIds.map { ids -> ids.contains(jobId) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )
    }


    fun saveJob(job: JobUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveJobUseCase(job.toDomain())
        }
    }

    fun deleteJob(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteJobUseCase(id)
        }
    }

    suspend fun isJobSaved(id: Long): Boolean {
        return isJobSavedUseCase(id)
    }

    fun updateJobStatus(jobId: Long, newStatus: JobStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            updateSavedJobStatusUseCase(jobId, newStatus)
        }
    }

    fun deleteAllJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllSavedJobsUseCase()
        }
    }


}
