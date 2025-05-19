package com.example.jobsearchapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import androidx.work.Configuration
import androidx.hilt.work.HiltWorkerFactory
//import com.google.firebase.ktx.Firebase

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}
