package com.example.jobsearchapplication.di

import com.example.jobsearchapplication.data.dataSources.remote.JobRemoteDataSource
import com.example.jobsearchapplication.data.repository.JobsRepositoryImpl
import com.example.jobsearchapplication.domain.repository.JobsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.jobsearchapplication.data.dataSources.local.dao.JobDao
import com.example.jobsearchapplication.data.dataSources.local.dao.TrackedJobsDao
import com.example.jobsearchapplication.data.repository.AuthRepositoryImpl
import com.example.jobsearchapplication.data.repository.SavedJobRepositoryImpl
import com.example.jobsearchapplication.data.repository.TrackedJobsRepositoryImpl
import com.example.jobsearchapplication.domain.repository.AuthRepository
import com.example.jobsearchapplication.domain.repository.SavedJobRepository
import com.example.jobsearchapplication.domain.repository.TrackedJobsRepository
import dagger.Binds

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideJobRepository(
        jobRemoteDataSource: JobRemoteDataSource
    ): JobsRepository {
        return JobsRepositoryImpl(jobRemoteDataSource)
    }

    @Provides
    fun provideSavedJobRepository(jobDao: JobDao): SavedJobRepository {
        return SavedJobRepositoryImpl(jobDao)
    }

    @Provides
    fun provideTrackedJobRepository(trackedJobsDao: TrackedJobsDao): TrackedJobsRepository {
        return TrackedJobsRepositoryImpl(trackedJobsDao)
    }


}