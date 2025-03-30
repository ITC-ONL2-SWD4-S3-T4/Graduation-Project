package com.example.jobsearchapplication.ui.screens.job_search_screen

import com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel.Category
import com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel.Company
import com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel.Location

data class JobUiModel(
    val id: Long,
    val title: String,
    val location: String,
    val company: String,
    val salary_min: Double?,
    val salary_max: Double?,
    val contract_time: String?,
    val contract_type: String?,
    val created: String,
    val category: String,
    val description: String,
    val redirect_url: String


    )



