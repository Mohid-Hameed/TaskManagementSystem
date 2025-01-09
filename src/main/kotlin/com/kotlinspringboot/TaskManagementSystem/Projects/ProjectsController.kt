package com.kotlinspringboot.TaskManagementSystem.Projects

import com.fasterxml.jackson.annotation.JsonView
import com.kotlinspringboot.TaskManagementSystem.CreateProjectDTO
import com.kotlinspringboot.TaskManagementSystem.UpdateProjectDTO
import com.kotlinspringboot.TaskManagementSystem.Views
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectController(private val projectService: ProjectService) {

    @GetMapping
    @JsonView(Views.ProjectWithManager::class)
    fun getAllProjects() = projectService.getAllProjects()

    @GetMapping("/{id}")
    @JsonView(Views.ProjectWithManager::class)
    fun getProjectById(@PathVariable id: Long) = projectService.getProjectById(id)

    @PostMapping
    fun createProject(@RequestBody createProjectDTO: CreateProjectDTO) = projectService.createProject(createProjectDTO)

    @PutMapping("/{id}")
    fun updateProject(
        @PathVariable id: Long,
        @RequestBody updateProjectDTO: UpdateProjectDTO
    ) = projectService.updateProject(id, updateProjectDTO)

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: Long) = projectService.deleteProject(id)

    @GetMapping("/{id}/tasks")
    @JsonView(Views.TaskWithUserAndProject::class)
    fun getProjectTasks(@PathVariable id: Long) = projectService.getProjectTasks(id)
}
