package data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartwallet.Data.Local.Entity.CardEntity

@Dao
interface CardDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(
        card: CardEntity
    )


    @Query("SELECT * FROM card LIMIT 1")
    suspend fun getCard(): CardEntity?

}