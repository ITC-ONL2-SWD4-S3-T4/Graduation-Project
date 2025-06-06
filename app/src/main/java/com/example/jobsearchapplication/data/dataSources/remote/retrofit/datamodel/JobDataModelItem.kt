package com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel

data class JobDataModelItem(
    val id: Long,
    val title: String,
    val description: String,
    val salary_min: Double?,
    val salary_max: Double?,
    val contract_time: String,
    val contract_type: String?,
    val created: String,
    val redirect_url: String,
    val company: Company,
    val location: Location,
    val category: Category
)
