package com.kotlinspringboot.TaskManagementSystem.Tasks

import com.fasterxml.jackson.annotation.JsonView
import com.kotlinspringboot.TaskManagementSystem.CreateTaskDTO
import com.kotlinspringboot.TaskManagementSystem.Views
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService) {

    @GetMapping
    @JsonView(Views.TaskWithUserAndProject::class)
    fun getAllTasks() = taskService.getAllTasks()

    @GetMapping("/{id}")
    @JsonView(Views.TaskWithUserAndProject::class)
    fun getTaskById(@PathVariable id: Long) = taskService.getTaskById(id)

    @PostMapping
    fun createTask(@RequestBody createTaskDTO: CreateTaskDTO) = taskService.createTask(createTaskDTO)

    @PutMapping("/{id}")
    @JsonView(Views.TaskWithUserAndProject::class)
    fun updateTask(@PathVariable id: Long, @RequestBody task: Tasks) = taskService.updateTask(id, task)

    @PutMapping("/{taskId}/assign/project/{projectId}")
    @JsonView(Views.TaskWithUserAndProject::class)
    fun assignTaskToProject(@PathVariable taskId: Long, @PathVariable projectId: Long) =
        taskService.assignTaskToProject(taskId, projectId)

    @PutMapping("/{taskId}/assign/user/{userId}")
    @JsonView(Views.TaskWithUserAndProject::class)
    fun assignTaskToUser(@PathVariable taskId: Long, @PathVariable userId: Long) =
        taskService.assignTaskToUser(taskId, userId)

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) = taskService.deleteTask(id)
}
