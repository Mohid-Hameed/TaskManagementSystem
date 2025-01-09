package com.kotlinspringboot.TaskManagementSystem.Users

import com.fasterxml.jackson.annotation.JsonView
import com.kotlinspringboot.TaskManagementSystem.UpdateUserDTO
import com.kotlinspringboot.TaskManagementSystem.Views
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    @GetMapping
    @JsonView(Views.UserWithTasksAndProjects::class)
    fun getAllUsers() = userService.getAllUsers()

    @GetMapping("/{id}")
    @JsonView(Views.UserWithTasksAndProjects::class)
    fun getUserById(@PathVariable id: Long) = userService.getUserById(id)

    @PostMapping
    fun createUser(@RequestBody user: Users) = userService.createUser(user)

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody updateUserDTO: UpdateUserDTO
    ) = userService.updateUser(id, updateUserDTO)


//    @DeleteMapping("/{id}")
//    fun deleteUser(@PathVariable id: Long) = userService.deleteUser(id)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<String> {
        userService.deleteUser(id)
        return ResponseEntity.ok("User with id $id has been deleted.")
    }

    @GetMapping("/{id}/tasks")
    @JsonView(Views.TaskWithUserAndProject::class)
    fun getUserTasks(@PathVariable id: Long) = userService.getUserTasks(id)

    @GetMapping("/{id}/projects")
    @JsonView(Views.ProjectWithManager::class)
    fun getUserProjects(@PathVariable id: Long) = userService.getUserProjects(id)
}
