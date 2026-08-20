package data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.smartwallet.Data.Local.Entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(
        user: UserEntity
    ): Long


    @Query(
        """
        SELECT * FROM users
        WHERE id = :userId
        """
    )
    suspend fun getUserById(
        userId: Int
    ): UserEntity?


    @Query(
        """
        SELECT * FROM users
        WHERE name = :name AND monthlyIncome = :monthlyIncome
        LIMIT 1
        """
    )
    suspend fun getUserByCredentials(
        name: String,
        monthlyIncome: Double
    ): UserEntity?

}