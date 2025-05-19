package com.example.jobsearchapplication.ui.mapper

import com.example.jobsearchapplication.data.dataSources.local.entity.ReminderEntity
import com.example.jobsearchapplication.domain.model.JobsDomainModel
import com.example.jobsearchapplication.ui.common_components.JobStatus
import com.example.jobsearchapplication.ui.screens.add_reminder_screen.ReminderUiModel
import com.example.jobsearchapplication.ui.screens.job_search_screen.JobUiModel

fun ReminderEntity.toUi(): ReminderUiModel = ReminderUiModel(id, reminderTitle, reminderText, reminderTime)

fun List<ReminderEntity>.toReminderListUi(): List<ReminderUiModel> {
    return this.map { item ->
        ReminderUiModel(
            id = item.id,
            reminderTitle = item.reminderTitle,
            reminderText =  item.reminderText,
            reminderTime = item.reminderTime,
        )
    }
}
