package com.example.jobsearchapplication.di

import com.example.jobsearchapplication.domain.repository.SavedJobRepository
import com.example.jobsearchapplication.domain.repository.TrackedJobsRepository
import com.example.jobsearchapplication.domain.usecase.DeleteAllSavedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.DeleteAllTrackedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.DeleteJobUseCase
import com.example.jobsearchapplication.domain.usecase.DeleteTrackedJobUseCase
import com.example.jobsearchapplication.domain.usecase.GetSavedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.GetTrackedJobsUseCase
import com.example.jobsearchapplication.domain.usecase.IsJobSavedUseCase
import com.example.jobsearchapplication.domain.usecase.SaveJobUseCase
import com.example.jobsearchapplication.domain.usecase.SaveTrackedJobUseCase
import com.example.jobsearchapplication.domain.usecase.UpdateSavedJobsStatusUseCase
import com.example.jobsearchapplication.domain.usecase.UpdateTrackedJobStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideSaveJobUseCase(repository: SavedJobRepository): SaveJobUseCase {
        return SaveJobUseCase(repository)
    }

    @Provides
    fun provideGetSavedJobsUseCase(repository: SavedJobRepository): GetSavedJobsUseCase {
        return GetSavedJobsUseCase(repository)
    }

    @Provides
    fun provideDeleteJobUseCase(repository: SavedJobRepository): DeleteJobUseCase {
        return DeleteJobUseCase(repository)
    }

    @Provides
    fun provideIsJobSavedUseCase(repository: SavedJobRepository): IsJobSavedUseCase {
        return IsJobSavedUseCase(repository)
    }

    @Provides
    fun provideUpdateSavedJobUseCase(repository: SavedJobRepository): UpdateSavedJobsStatusUseCase {
        return UpdateSavedJobsStatusUseCase(repository)
    }

    @Provides
    fun provideDeleteAllSavedJobsUseCase(repository: SavedJobRepository): DeleteAllSavedJobsUseCase {
        return DeleteAllSavedJobsUseCase(repository)
    }



    @Provides
    fun provideTrackJobUseCase(repository: TrackedJobsRepository): SaveTrackedJobUseCase {
        return SaveTrackedJobUseCase(repository)
    }

    @Provides
    fun provideGetTrackedJobsUseCase(repository: TrackedJobsRepository): GetTrackedJobsUseCase {
        return GetTrackedJobsUseCase(repository)
    }

    @Provides
    fun provideDeleteTrackedJobUseCase(repository: TrackedJobsRepository): DeleteTrackedJobUseCase {
        return DeleteTrackedJobUseCase(repository)
    }

    @Provides
    fun provideUpdateTrackedJobUseCase(repository: TrackedJobsRepository): UpdateTrackedJobStatusUseCase {
        return UpdateTrackedJobStatusUseCase(repository)
    }

    @Provides
    fun provideDeleteAllTrackedJobsUseCase(repository: TrackedJobsRepository): DeleteAllTrackedJobsUseCase {
        return DeleteAllTrackedJobsUseCase(repository)
    }


}