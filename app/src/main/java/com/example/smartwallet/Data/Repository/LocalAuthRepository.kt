package com.example.smartwallet.Data.Repository

import com.example.smartwallet.Domain.Models.User
import com.example.smartwallet.Domain.Repository.AuthRepository
import com.example.smartwallet.Data.Datastore.UserSession
import data.local.dao.UserDao
import com.example.smartwallet.Data.Mapper.UserMapper

class LocalAuthRepository(
    private val userSession: UserSession,
    private val userDao: UserDao
) : AuthRepository {
    //How do i implement failure handling?
    override suspend fun login(User: User) {
        val user = userDao.getUserByCredentials(User.name, User.monthlyIncome)
        if (user != null) {userSession.saveUserId(user.id.toLong())}
    }

    override suspend fun register(
        User: User
    ) {
        val userEntity = with(UserMapper()){
            User.toEntity()
        }
        val userId = userDao.insertUser(userEntity)
        userSession.saveUserId(userId)
    }

    override suspend fun getCurrentUser(): User? {
        val userId = userSession.getUserId() ?: return null
        val userEntity = userDao.getUserById(userId.toInt()) ?: return null
        val mapper = UserMapper()

        val user = with(mapper){
            userEntity.toUser()
        }
        return user
    }

    override suspend fun logout() : Boolean{
        if (userSession.getUserId() != null) {
            userSession.clearUserId()
            return true
        }
        else{
            return false
        }
    }
}
//handling is to avoid throwing exceptions for "expected" failures (like invalid credentials). Instead, you should return a Result wrapper.