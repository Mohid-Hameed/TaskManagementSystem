package com.kotlinspringboot.TaskManagementSystem.Users

import com.fasterxml.jackson.annotation.JsonView
import com.kotlinspringboot.TaskManagementSystem.Projects.Projects
import com.kotlinspringboot.TaskManagementSystem.Tasks.Tasks
import com.kotlinspringboot.TaskManagementSystem.Views
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Users(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Basic::class)
    val id: Long = 0,

    @Column(nullable = false)
    @JsonView(Views.Basic::class)
    var name: String,

    @Column(nullable = false, unique = true)
    @JsonView(Views.Basic::class)
    var email: String,

    @Column(nullable = false, updatable = false)
    @JsonView(Views.Basic::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonView(Views.UserWithTasksAndProjects::class)
    val tasks: List<Tasks> = emptyList(),

    @OneToMany(mappedBy = "manager", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonView(Views.UserWithTasksAndProjects::class)
    val projects: List<Projects> = emptyList()
)