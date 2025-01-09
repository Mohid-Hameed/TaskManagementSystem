package com.kotlinspringboot.TaskManagementSystem

import com.kotlinspringboot.TaskManagementSystem.Projects.Projects
import com.kotlinspringboot.TaskManagementSystem.Tasks.Tasks
import com.kotlinspringboot.TaskManagementSystem.Users.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Users, Long>
interface ProjectRepository : JpaRepository<Projects, Long>
interface TaskRepository : JpaRepository<Tasks, Long>