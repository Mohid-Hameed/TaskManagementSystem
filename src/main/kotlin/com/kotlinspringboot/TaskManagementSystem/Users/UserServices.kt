package com.kotlinspringboot.TaskManagementSystem.Users

import com.kotlinspringboot.TaskManagementSystem.Tasks.Tasks
import com.kotlinspringboot.TaskManagementSystem.Projects.Projects
import com.kotlinspringboot.TaskManagementSystem.UpdateUserDTO
import com.kotlinspringboot.TaskManagementSystem.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<Users> = userRepository.findAll()

    fun getUserById(id: Long): Users = userRepository.findById(id)
        .orElseThrow { RuntimeException("User not found!") }

    fun createUser(user: Users): Users = userRepository.save(user)

    fun updateUser(id: Long, updateUserDTO: UpdateUserDTO): Users {
        val existingUser = getUserById(id)

        // Update only provided fields
        if (updateUserDTO.name != null) {
            existingUser.name = updateUserDTO.name
        }
        if (updateUserDTO.email != null) {
            existingUser.email = updateUserDTO.email
        }

        return userRepository.save(existingUser)
    }


    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    fun getUserTasks(id: Long): List<Tasks> {
        val user = getUserById(id)
        return user.tasks
    }

    fun getUserProjects(id: Long): List<Projects> {
        val user = getUserById(id)
        return user.projects
    }
}
