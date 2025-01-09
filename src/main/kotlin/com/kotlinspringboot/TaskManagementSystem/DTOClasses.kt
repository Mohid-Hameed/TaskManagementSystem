package com.kotlinspringboot.TaskManagementSystem

import com.kotlinspringboot.TaskManagementSystem.Tasks.Tasks

data class UpdateProjectDTO(
    val name: String? = null,
    val description: String? = null
)

data class UpdateUserDTO(
    val name: String? = null,
    val email: String? = null
)

data class CreateProjectDTO(
    val name: String,
    val description: String? = null,
    val managerId: Long
)

data class CreateTaskDTO(
    val title: String,
    val description: String,
    val status: String? = "PENDING",
    val priority: Int? = 0,
    val userId: Long,
    val projectId: Long,
)

class Views {
    interface Basic
    interface UserWithTasksAndProjects : Basic
    interface TaskWithUserAndProject : Basic
    interface ProjectWithManager : Basic
}

