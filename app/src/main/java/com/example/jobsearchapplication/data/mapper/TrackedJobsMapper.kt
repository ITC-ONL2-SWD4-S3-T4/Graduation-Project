package com.example.jobsearchapplication.data.mapper
import com.example.jobsearchapplication.data.dataSources.local.entity.JobEntity
import com.example.jobsearchapplication.data.dataSources.local.entity.TrackedJobsEntity
import com.example.jobsearchapplication.domain.model.JobsDomainModel

fun TrackedJobsEntity.toDomain(): JobsDomainModel = JobsDomainModel(id, title, description, salary_min, salary_max, contract_time, contract_type, created, redirect_url, company, location, category, status)

fun JobsDomainModel.toTrackedJobsEntity(): TrackedJobsEntity = TrackedJobsEntity(id, title, description, salary_min, salary_max, contract_time, contract_type, created, redirect_url, company, location, category, status)


