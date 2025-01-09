package com.kotlinspringboot.TaskManagementSystem.Projects

import com.fasterxml.jackson.annotation.JsonView
import com.kotlinspringboot.TaskManagementSystem.Tasks.Tasks
import com.kotlinspringboot.TaskManagementSystem.Users.Users
import com.kotlinspringboot.TaskManagementSystem.Views
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Projects(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Basic::class)
    val id: Long = 0,

    @Column(nullable = false)
    @JsonView(Views.Basic::class)
    var name: String,

    @Column
    @JsonView(Views.Basic::class)
    var description: String? = null,

    @Column(nullable = false, updatable = false)
    @JsonView(Views.Basic::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @JsonView(Views.Basic::class)
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    @JsonView(Views.ProjectWithManager::class)
    val manager: Users,

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonView(Views.ProjectWithManager::class)
    val tasks: List<Tasks> = listOf()
)
