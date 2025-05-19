package com.example.jobsearchapplication.domain.usecase

import com.example.jobsearchapplication.domain.repository.ReminderRepository
import com.example.jobsearchapplication.domain.repository.SavedJobRepository
import java.time.LocalDateTime
import javax.inject.Inject

class ScheduleReminderUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(
        reminderText: String,
        reminderTime: LocalDateTime,
        reminderTitle: String
    ) = repository.scheduleReminder(
        reminderText = reminderText ,
        reminderTime = reminderTime,
        reminderTitle = reminderTitle

    )
}

class UpdateReminderUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(
        reminderId: Int,
        reminderText: String,
        reminderTime: LocalDateTime,
        reminderTitle: String
    ) = repository.updateReminder(
        reminderId = reminderId,
        reminderText = reminderText ,
        reminderTime = reminderTime,
        reminderTitle = reminderTitle

    )
}

class DeleteReminderUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(
        reminderId: Int,
    ) = repository.cancelReminder(
        reminderId = reminderId,
    )
}

class DeleteAllRemindersUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke() = repository.deleteAll()
}