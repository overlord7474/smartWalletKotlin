package com.example.smartwallet.Data.Mapper

import com.example.smartwallet.Data.Local.Entity.UserEntity
import com.example.smartwallet.Domain.Models.User

class UserMapper {
    fun UserEntity.toUser() =
        User(
            id = id,
            name = name,
            monthlyIncome = monthlyIncome,
        )

    fun User.toEntity() =
        UserEntity(
            id = id,
            name = name,
            monthlyIncome = monthlyIncome,
        )
}

//A more common approach
//
//Most Android projects make these top-level extension functions instead of putting them inside a class. Then you don't need a UserMapper instance at all.