package com.example.jobsearchapplication.di

import com.example.jobsearchapplication.data.dataSources.remote.JobRemoteDataSource
import com.example.jobsearchapplication.data.repository.JobsRepositoryImpl
import com.example.jobsearchapplication.domain.repository.JobsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


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

}