package com.example.tokoelektronik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Smartphone(
    @PrimaryKey val id : String,
    val model : String,
    val warna : String,
    val storage : String,
    val tanggal_rilis : String,
    val sistem_operasi : String
)
