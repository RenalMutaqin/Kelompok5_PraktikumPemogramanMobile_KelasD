package com.example.TokoElektronik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Smartphone(
    @PrimaryKey val id : String,
    val model : String,
    val warna : String,
    val storage : Int,
    val tanggal_rilis : Int,
    val sistem_operasi : String
)
