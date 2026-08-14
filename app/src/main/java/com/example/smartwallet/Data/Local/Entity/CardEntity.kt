package com.example.smartwallet.Data.Local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cards"
)
data class CardEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,

    val number: String,

    val expiryDate: String,

    val cvv: String
)