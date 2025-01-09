package com.kotlinspringboot.TaskManagementSystem.Tasks

import com.kotlinspringboot.TaskManagementSystem.CreateTaskDTO
import com.kotlinspringboot.TaskManagementSystem.ProjectRepository
import com.kotlinspringboot.TaskManagementSystem.TaskRepository
import com.kotlinspringboot.TaskManagementSystem.UserRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val projectRepository: ProjectRepository
) {

    fun getAllTasks(): List<Tasks> = taskRepository.findAll().sortedBy { it.priority }

    fun getTaskById(id: Long): Tasks = taskRepository.findById(id)
        .orElseThrow { RuntimeException("Task not found!") }

    fun createTask(createTaskDTO: CreateTaskDTO): Tasks {
        val user =
            createTaskDTO.userId.let { userRepository.findById(it).orElseThrow { RuntimeException("User not found!") } }
        val project = createTaskDTO.projectId.let {
            projectRepository.findById(it).orElseThrow { RuntimeException("Project not found!") }
        }
        val task = Tasks(
            title = createTaskDTO.title,
            description = createTaskDTO.description,
            status = createTaskDTO.status ?: "PENDING",
            priority = createTaskDTO.priority ?: 0,
            user = user,
            project = project
        )
        return taskRepository.save(task)
    }

    fun updateTask(id: Long, updatedTask: Tasks): Tasks {
        val existingTask = getTaskById(id)
        return taskRepository.save(
            existingTask.copy(
                title = updatedTask.title,
                description = updatedTask.description,
                status = updatedTask.status,
                priority = updatedTask.priority,
                updatedAt = updatedTask.updatedAt
            )
        )
    }

    fun assignTaskToProject(taskId: Long, projectId: Long): Tasks {
        val task = getTaskById(taskId)
        val project = projectRepository.findById(projectId)
            .orElseThrow { RuntimeException("Project not found!") }
        val updatedTask = task.copy(project = project)
        return taskRepository.save(updatedTask)
    }

    fun assignTaskToUser(taskId: Long, userId: Long): Tasks {
        val task = getTaskById(taskId)
        val user = userRepository.findById(userId)
            .orElseThrow { RuntimeException("User not found!") }
        val updatedTask = task.copy(user = user)
        return taskRepository.save(updatedTask)
    }

    fun deleteTask(id: Long) {
        taskRepository.deleteById(id)
    }
}
