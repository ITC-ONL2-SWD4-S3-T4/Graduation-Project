package com.example.jobsearchapplication.ui.mapper

import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.ui.screens.job_search_screen.JobUiModel


fun List<JobsDomainModel>.toJobsUiModel(): List<JobUiModel> {
    return this.map { item ->
        JobUiModel(
            id = item.id,
            title = item.title,
         location =  item.location,
         company = item.company,
         salary_max = item.salary_max,
            salary_min = item.salary_min,
            contract_time = item.contract_time,
            contract_type = item.contract_type,
            created = item.created,
            category = item.category,
            description = item.description,
            redirect_url = item.redirect_url
        )
    }
}