package com.example.jobsearchapplication.di

import android.content.Context
import com.example.jobsearchapplication.data.dataSources.local.dao.ReminderDao
import com.example.jobsearchapplication.data.repository.ReminderRepositoryImpl
import com.example.jobsearchapplication.domain.repository.ReminderRepository
import com.example.jobsearchapplication.ui.common_components.NotificationHelper
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideReminderRepository(
        @ApplicationContext context: Context,
        notificationHelper: NotificationHelper,
        reminderDao: ReminderDao
    ): ReminderRepository = ReminderRepositoryImpl(context, reminderDao)

    @Provides
    fun provideNotificationHelper(
        @ApplicationContext context: Context
    ) = NotificationHelper(context)

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}