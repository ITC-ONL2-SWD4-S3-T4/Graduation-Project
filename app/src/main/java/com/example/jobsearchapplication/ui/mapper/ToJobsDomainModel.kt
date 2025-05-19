package com.example.jobsearchapplication.ui.mapper

import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.ui.screens.job_search_screen.JobUiModel

fun JobUiModel.toDomain(): JobsDomainModel = JobsDomainModel(id, title, description, salary_min, salary_max, contract_time, contract_type, created, redirect_url, company, location, category, status)
