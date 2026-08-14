package com.example.smartwallet.Data.Local.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.smartwallet.Data.Local.Entity.ExpenseEntity

@Dao
interface ExpenseDao {


    @Insert
    suspend fun insertExpense(
        expense: ExpenseEntity
    )


    @Query(
        "SELECT * FROM expenses ORDER BY createdAt DESC"
    )
    suspend fun getAllExpenses(): List<ExpenseEntity>


    @Query(
        """
        SELECT * FROM expenses 
        ORDER BY createdAt DESC 
        LIMIT 3
        """
    )
    suspend fun getLatestExpenses(): List<ExpenseEntity>


    @Query(
        "SELECT SUM(amount) FROM expenses"
    )
    suspend fun getTotalSpent(): Double?

}