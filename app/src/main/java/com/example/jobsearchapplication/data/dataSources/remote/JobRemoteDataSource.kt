package com.example.jobsearchapplication.data.dataSources.remote

import com.example.jobsearchapplication.data.dataSources.remote.retrofit.api.JobApi
import com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel.JobDataModel
import com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel.JobDataModelItem
import javax.inject.Inject

class JobRemoteDataSource @Inject constructor(
    private val jobApi: JobApi
) {
    suspend fun fetchJobs(): JobDataModel {
//        return jobApi.fetchJobs(appId = "9d0b649b",
//            appKey = "9bc4139d9f91cf3c0d6a70aad6561f70").body() as JobDataModel
        val response = jobApi.fetchJobs(
            appId = "9d0b649b",
            appKey = "9bc4139d9f91cf3c0d6a70aad6561f70"
        )

        if (response.isSuccessful) {
            return response.body() as JobDataModel
        } else {
            throw Exception("API Error: ${response.errorBody()?.string()}")
        }
    }
}