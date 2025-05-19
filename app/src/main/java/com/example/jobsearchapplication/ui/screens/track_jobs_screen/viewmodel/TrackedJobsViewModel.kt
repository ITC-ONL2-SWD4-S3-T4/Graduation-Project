package com.example.jobsearchapplication.ui.screens.track_jobs_screen.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.domain.usecase.DeleteAllSavedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.DeleteAllTrackedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.DeleteJobUseCase
import com.example.jobsearchapplication.domain.usecase.DeleteTrackedJobUseCase
import com.example.jobsearchapplication.domain.usecase.GetSavedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.GetTrackedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.IsJobSavedUseCase
import com.example.jobsearchapplication.domain.usecase.SaveJobUseCase
import com.example.jobsearchapplication.domain.usecase.SaveTrackedJobUseCase
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
import kotlinx.coroutines.flow.map


@HiltViewModel
class TrackedJobsViewModel @Inject constructor(
    private val saveTrackedJobUseCase: SaveTrackedJobUseCase,
    private val getTrackedJobsUseCase: GetTrackedJobsUseCase,
    private val deleteTrackedJobUseCase: DeleteTrackedJobUseCase,
    private val updateTrackedJobStatusUseCase: UpdateTrackedJobStatusUseCase,
    private val deleteAllTrackedJobsUseCase: DeleteAllTrackedJobsUseCase
) : ViewModel() {

    val savedJobs = getTrackedJobsUseCase()
        .map { jobs ->
            jobs.map { it.toUi() }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun saveJob(job: JobUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveTrackedJobUseCase(job.toDomain())
        }
    }

    fun deleteJob(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTrackedJobUseCase(id)
        }
    }

    fun updateJobStatus(jobId: Long, newStatus: JobStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTrackedJobStatusUseCase(jobId, newStatus)
        }
    }

    fun deleteAllJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllTrackedJobsUseCase()
        }
    }
}
