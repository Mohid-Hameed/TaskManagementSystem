package com.kotlinspringboot.TaskManagementSystem.Tasks

import com.fasterxml.jackson.annotation.JsonView
import com.kotlinspringboot.TaskManagementSystem.Projects.Projects
import com.kotlinspringboot.TaskManagementSystem.Users.Users
import com.kotlinspringboot.TaskManagementSystem.Views
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Tasks(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Basic::class)
    val id: Long = 0,

    @Column(nullable = false)
    @JsonView(Views.Basic::class)
    val title: String,

    @Column(nullable = false)
    @JsonView(Views.Basic::class)
    val description: String,

    @Column(nullable = false)
    @JsonView(Views.Basic::class)
    val status: String = "PENDING",

    @Column(nullable = false)
    @JsonView(Views.Basic::class)
    val priority: Int = 0,

    @Column(nullable = false, updatable = false)
    @JsonView(Views.Basic::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @JsonView(Views.Basic::class)
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.TaskWithUserAndProject::class)
    val user: Users?,

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonView(Views.TaskWithUserAndProject::class)
    val project: Projects?
)
