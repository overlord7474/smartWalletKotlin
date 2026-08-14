package com.example.smartwallet.Data.Mapper

import com.example.smartwallet.Data.Local.Entity.CardEntity
import com.example.smartwallet.Domain.Models.Card

class CardMapper {
    fun CardEntity.toCard() =
        Card(
            number,
            expiryDate,
            cvv
        )

    fun Card.toEntity(uId: Int) =
        CardEntity(
            number = number,
            expiryDate = expiryDate,
            cvv = cvv,
            userId = uId
        )
}