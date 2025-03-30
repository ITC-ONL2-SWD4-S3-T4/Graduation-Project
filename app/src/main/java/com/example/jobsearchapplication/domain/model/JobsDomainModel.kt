package com.example.jobsearchapplication.domain.model

import com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel.Category
import com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel.Company
import com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel.Location

data class JobsDomainModel(

val id: Long,
val title: String,
val description: String,
val salary_min: Double?,
val salary_max: Double?,
val contract_time: String?,
val contract_type: String?,
val created: String,
val redirect_url: String,
val company: String,
val location: String,
val category: String

)