package com.example.smartwallet.Data.Repository

import com.example.smartwallet.Domain.Models.User
import com.example.smartwallet.Domain.Repository.AuthRepository
import com.example.smartwallet.Data.Datastore.UserSession
import data.local.dao.UserDao
import com.example.smartwallet.Data.Mapper.toEntity
import com.example.smartwallet.Data.Mapper.toUser

class LocalAuthRepository(
    private val userSession: UserSession,
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun login(user: User): Result<Unit> {
        return try {
            val foundUser = userDao.getUserByCredentials(user.name)
                ?: return Result.failure(Exception("User not found"))

            userSession.saveUserId(foundUser.id.toLong())
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            Result.failure(e)
        }
    }

    override suspend fun register(
        user: User
    ): Result<Unit> {
        return try {
            val userEntity = user.toEntity()
            val userId = userDao.insertUser(userEntity)
                ?: return Result.failure(Exception("Insert Fail for the following reasons (hey how is my userDao db query failures supposed to return detailed error logs? dont i need a logger for my app or im blind?)"))
            userSession.saveUserId(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            Result.failure(e)
        }
    }

    override suspend fun getCurrentUser(): User? {
        val userId = userSession.getUserId() ?: return null
        val userEntity = userDao.getUserById(userId.toInt()) ?: return null //how do i return something like "db connection failure" or shit like that? This isnt supposed to be that binary

        return userEntity.toUser()
    }

    override suspend fun logout() : Result<Unit>{
        if (userSession.getUserId() != null) {
            userSession.clearUserId()
            return Result.success(Unit)
        }
        else{
            return Result.failure(Exception("No user is currently logged in."))
        }
    }
}
/**
 * TODO: FUTURE REFACTOR - Upgrade Error Handling & Logging
 *
 * Current state: Barebones and unsafe. Relies on raw try-catch blocks and basic exceptions,
 * which can mask underlying database issues and lacks detailed diagnostics.
 *
 * Steps to upgrade one day:
 * 1. Implement a dedicated Logging utility or dependency (e.g., Timber / Napier) to capture
 *    production crashes and lifecycle events cleanly.
 * 2. Replace generic Exception handling with specific SQLiteExceptions (like SQLiteConstraintException)
 *    to parse exact raw SQLite error messages (e.g., unique constraints, disk errors).
 * 3. Transition from Kotlin's built-in Result<T> to a custom sealed class (e.g., AppResult<T>)
 *    if fine-grained error states (NetworkError, DatabaseCrash, DuplicateUser) are needed for the UI.
 * 4. Ensure all Data Layer errors are properly mapped to Domain-level failures before reaching ViewModels.
 */

