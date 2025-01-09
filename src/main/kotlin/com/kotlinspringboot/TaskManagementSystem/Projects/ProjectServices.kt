package com.kotlinspringboot.TaskManagementSystem.Projects

import com.kotlinspringboot.TaskManagementSystem.CreateProjectDTO
import com.kotlinspringboot.TaskManagementSystem.Tasks.Tasks
import com.kotlinspringboot.TaskManagementSystem.ProjectRepository
import com.kotlinspringboot.TaskManagementSystem.UpdateProjectDTO
import com.kotlinspringboot.TaskManagementSystem.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProjectService(private val projectRepository: ProjectRepository, private val userRepository:UserRepository) {

    fun getAllProjects(): List<Projects> = projectRepository.findAll()

    fun getProjectById(id: Long): Projects = projectRepository.findById(id)
        .orElseThrow { RuntimeException("Project not found!") }

    fun createProject(createProjectDTO: CreateProjectDTO): Projects {
        val manager = userRepository.findById(createProjectDTO.managerId)
            .orElseThrow { RuntimeException("Manager not found!") }
        val project = Projects(
            name = createProjectDTO.name,
            description = createProjectDTO.description,
            manager = manager
        )
        return projectRepository.save(project)
    }

    fun updateProject(id: Long, updateProjectDTO: UpdateProjectDTO): Projects {
        val existingProject = getProjectById(id)

        if (updateProjectDTO.name != null) {
            existingProject.name = updateProjectDTO.name
        }
        if (updateProjectDTO.description != null) {
            existingProject.description = updateProjectDTO.description
        }

        existingProject.updatedAt = LocalDateTime.now()

        return projectRepository.save(existingProject)
    }



    fun deleteProject(id: Long) {
        projectRepository.deleteById(id)
    }

    fun getProjectTasks(id: Long): List<Tasks> {
        val project = getProjectById(id)
        return project.tasks
    }
}
