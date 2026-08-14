package com.example.smartwallet.Data.Local.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.smartwallet.Data.Local.DAO.ExpenseDao
import com.example.smartwallet.Data.Local.Entity.CardEntity
import com.example.smartwallet.Data.Local.Entity.ExpenseEntity
import com.example.smartwallet.Data.Local.Entity.UserEntity
import data.local.dao.CardDao
import data.local.dao.UserDao

@Database(
    entities = [
        UserEntity::class,
        CardEntity::class,
        ExpenseEntity::class
    ],
    version = 1
)
abstract class ExpenseDatabase : RoomDatabase() {


    abstract fun cardDao(): CardDao
    abstract fun userDao(): UserDao

    abstract fun expenseDao(): ExpenseDao

}